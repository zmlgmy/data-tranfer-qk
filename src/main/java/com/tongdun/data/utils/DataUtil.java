package com.tongdun.data.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class DataUtil {

	private static final Logger log = LoggerFactory.getLogger(DataUtil.class);

	private static final String TIDS=ProperitesUtil.getPropertyValue("forseti.tid");


	private DataUtil() {
	}

	private static DataUtil dataUtil = new DataUtil();

	public static DataUtil getInstance() {
		return dataUtil;
	}

	public static String md5(String src) {
		if (null != src && !"".equals(src)) {
			try {
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.reset();
				m.update(src.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1, digest);
				String hashtext = bigInt.toString(16);
				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				return hashtext;
			} catch (NoSuchAlgorithmException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static String convertOSPath(String sysPath) {
		File file = new File(sysPath);

		String filePath = file.getPath();

		StringBuffer path = new StringBuffer();
		// 非windows os 绝对路径
		if (filePath.startsWith("/")) {
			path.append(filePath);
		} else if (windowsAbsolutepath(filePath)) { // windows os 绝对路径
			path.append(filePath);
		} else if (filePath.startsWith("~")) { // home目录
			if (filePath.length() == 1) { // 仅仅是一个~
				path.append(System.getProperty("user.home"));
			} else { // 以~/开头
				path.append(System.getProperty("user.home")).append(filePath.substring(1));
			}
		} else { // 相对路径
			throw new RuntimeException("请指定文件的目录，" + "格式[~ start stand for home ,\nexample: "
					+ "~/dms on linux is /home/yourname/dms;\n" + "on windows is c:\\users\\yourname\\dms \nor C: "
					+ "or D: etc is absoulute path if not above format it will use  relative path");
		}

		File f = new File(path.toString());
		if (!f.exists()) {
			if (!f.mkdirs()) {
				throw new RuntimeException("can not mkdirs of " + filePath);
			}
		}

		if (!f.canWrite() || !f.canRead()) {
			throw new RuntimeException(f.getAbsolutePath() + " can not read or write");
		}

		return f.getAbsolutePath();
	}

	private static boolean windowsAbsolutepath(String filePath) {
		for (byte b = 65; b < 91; b++) {
			if (filePath.toUpperCase().startsWith(String.valueOf(((char) b) + ":"))) {
				return true;
			}
		}
		return false;
	}

	public static String getTid(Map<String,Object> params){
//		String traType = changeToString(params.get("traType"));
//		String subNode = changeToString(params.get("subNode"));
//		String subDate = changeToString(params.get("subDate"));
//		String transNo = changeToString(params.get("transNo"));

		StringBuffer tid=new StringBuffer();
		if(!StringUtils.isBlank(TIDS)){
			String[] split = TIDS.split(",");
			for(String s : split){
				if(params.get(s)!=null){
					tid.append(params.get(s).toString());
				}
			}
		}
//		log.info("生成唯一标识tid:{}",tid.toString());

		return tid.toString();
	}

	public static String changeToString(Object o){
		String oStr="";
		if(o!=null){
			oStr=o.toString();
		}
		return oStr;
	}
	
}
