package com.tongdun.data.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.*;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
     
    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient httpclient = null;
     
    static {
        try {
            SSLContext sslContext = SSLContexts.custom().useTLS().build();
            sslContext.init(null,
                    new TrustManager[] { new X509TrustManager() {
                         
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
 
                        public void checkClientTrusted(
                                X509Certificate[] certs, String authType) {
                        }
 
                        public void checkServerTrusted(
                                X509Certificate[] certs, String authType) {
                        }
                    }}, null);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext))
                    .build();
             
            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();
            // Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(200)
                .setMaxLineLength(2000)
                .build();
            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();
            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(200);
            connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        }
    }
     
    public static String postJsonBody(String url, int timeout, Map<String, Object> map, String encoding){
        HttpPost post = new HttpPost(url);
        try {
            post.setHeader("Content-type", "application/json");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .setExpectContinueEnabled(false).build();
            post.setConfig(requestConfig);
             
            String str1 = JSON.toJSONString(map).replace("\\", "");
            post.setEntity(new StringEntity(str1, encoding));
            logger.info("[HttpUtils Post] begin invoke url:" + url + " , params:"+str1);
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                try {
                    if(entity != null){
                        String str = EntityUtils.toString(entity, encoding);
                        logger.info("[HttpUtils Post]Debug response, url :" + url + " , response string :"+str);
                        return str;
                    }
                } finally {
                    if(entity != null){
                        entity.getContent().close();
                    }
                }
            } finally {
                if(response != null){
                    response.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException", e);
        } catch (Exception e) {
            logger.error("Exception", e);
        } finally {
            post.releaseConnection();
        }
        return "";
    }
     
    public static String invokeGet(String url, Map<String, Object> params, int connectTimeout) {
        String responseString = null;
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(connectTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout).build();
         
        URI up = buildUri(url,params);
        HttpGet get = new HttpGet(up);
        logger.info("[HttpUtils Get] begin invoke:" + up.toString());
        get.setConfig(requestConfig);
         
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            try {
            	if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            		logger.warn("[HttpClientUtil] invoke failed, response status: " + response.getStatusLine().getStatusCode());
                    return null;
            	}
                HttpEntity entity = response.getEntity();
                try {
                    if(entity != null){
                        responseString = EntityUtils.toString(entity, Consts.UTF_8);
                    }
                } finally {
                    if(entity != null){
                        entity.getContent().close();
                    }
                }
            } catch (Exception e) {
                logger.error(String.format("[HttpClientUtil Get]get response error, url:%s", up.toString()), e);
                return responseString;
            } finally {
                if(response != null){
                    response.close();
                }
            }
            logger.info(String.format("[HttpClientUtil Get]Debug url:%s , response string %s:", up.toString(), responseString));
        } catch (SocketTimeoutException e) {
            logger.error(String.format("[HttpClientUtil Get]invoke get timout error, url:%s", up.toString()), e);
            return responseString;
        } catch (Exception e) {
            logger.error(String.format("[HttpClientUtil Get]invoke get error, url:%s", up.toString()), e);
        } finally {
            get.releaseConnection();
        }
        return responseString;
    }
    
    /***
     * 拼接调用的URL
     *
     * @param url
     * @param parametersMap 业务数据，作为参数
     * @return
     */
    private static URI buildUri(String url, Map parametersMap) {
        if (null == parametersMap || parametersMap.isEmpty()) {
            return URI.create(url);
        }
        ArrayList list = new ArrayList(parametersMap.size());
        Iterator iterator = parametersMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            list.add(entry.getKey().toString().trim() + "=" + entry.getValue().toString().trim());
        }

        return list.isEmpty() ? URI.create(url) : URI.create(url + "?" + StringUtils.join(list, "&"));
    }
     
    public final static int connectTimeout = 5000;
    
    /**
     * HTTPS请求，默认超时为500ms
     * @param reqURL
     * @param params
     * @return
     */
    public static String connectPostHttps(String reqURL, Map<String, Object> params) {
 
        String responseContent = null;
         
        HttpPost httpPost = new HttpPost(reqURL); 
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(connectTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectTimeout).build();
             
            List<NameValuePair> formParams = new ArrayList<NameValuePair>(); 
            httpPost.setConfig(requestConfig);
            // 绑定到请求 Entry
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, Consts.UTF_8));
            CloseableHttpResponse response = httpclient.execute(httpPost);
             try {
            	 int statusCode = response.getStatusLine().getStatusCode();
                 if (statusCode != HttpStatus.SC_OK) {
                	 logger.warn("[HttpClientUtil] invoke failed, response status: " + statusCode);
                     return null;
                 }
                // 执行POST请求
                HttpEntity entity = response.getEntity(); // 获取响应实体
                try {
                    if (null != entity) {
                        responseContent = EntityUtils.toString(entity, Consts.UTF_8);
                        return responseContent;
                    }else{
                    	logger.warn("[HttpClientUtil] invoke failed, response output is null!");
                    	return null;
                    }
                } finally {
                    if(entity != null){
                        entity.getContent().close();
                    }
                }
            } finally {
                if(response != null){
                    response.close();
                }
            }
        } catch (ClientProtocolException e) {
            logger.error("[HttpClientUtil] ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("[HttpClientUtil] IOException", e);
        } finally {
            httpPost.releaseConnection();
        }
        return null;
         
    }

    public static void writeJSON(HttpServletResponse response, Object result) {
        if (response != null && result != null) {
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.print(JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
            } catch (IOException e) {
                e.getMessage();
            } finally {
                if (out != null) {
                    out.flush();
                }
            }
        }
    }
 
}