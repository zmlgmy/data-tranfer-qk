package com.tongdun.data.singleton;

import static com.tongdun.data.utils.PropertiesCommon.DXY_SECRET_KEY;
import static com.tongdun.data.utils.PropertiesCommon.QK_DXY_EVENT_ID;
import static com.tongdun.data.utils.PropertiesCommon.QK_SECRET_KEY;

import java.util.HashMap;
import java.util.Map;

import com.tongdun.data.utils.APIConfig;

/**
 * 
 * @author yxw
 *
 */
public class ProductionCodeToEventIdCache {

	public Map<String, Object> productionCodeToEventIdCache = new HashMap<>();

	private static ProductionCodeToEventIdCache productionCodeToEventIdCacheObj;

	private ProductionCodeToEventIdCache() {
		init();
	}

	public static ProductionCodeToEventIdCache getSingleton() {
		if (productionCodeToEventIdCacheObj == null) {
			synchronized (ProductionCodeToEventIdCache.class) {
				if (productionCodeToEventIdCacheObj == null) {
					productionCodeToEventIdCacheObj = new ProductionCodeToEventIdCache();
				}
			}
		}
		return productionCodeToEventIdCacheObj;
	}

	public void init() {

		productionCodeToEventIdCache.clear();

		// 钱客/店小友支付场景
		APIConfig qkConfig = new APIConfig();
		qkConfig.setSecretKey(QK_SECRET_KEY);
		qkConfig.setEventId(QK_DXY_EVENT_ID);
		qkConfig.setAppName("qk");
		productionCodeToEventIdCache.put("qk", qkConfig);

		APIConfig dxyConfig = new APIConfig();
		dxyConfig.setSecretKey(DXY_SECRET_KEY);
		dxyConfig.setEventId(QK_DXY_EVENT_ID);
		dxyConfig.setAppName("dxy");
		productionCodeToEventIdCache.put("dxy", dxyConfig);
		
		//钱客/店小友提现
		APIConfig qkWithdrawConfig = new APIConfig();
		qkWithdrawConfig.setSecretKey(QK_SECRET_KEY);
		qkWithdrawConfig.setEventId("withdraw");
		qkWithdrawConfig.setAppName("qk");
		productionCodeToEventIdCache.put("qk_withdraw", qkWithdrawConfig);
		
		APIConfig dxyWithdrawConfig = new APIConfig();
		dxyWithdrawConfig.setSecretKey(DXY_SECRET_KEY);
		dxyWithdrawConfig.setEventId("withdraw");
		dxyWithdrawConfig.setAppName("dxy");
		productionCodeToEventIdCache.put("dxy_withdraw", dxyWithdrawConfig);
		
		//钱客/店小友提现
		APIConfig qkRemitConfig = new APIConfig();
		qkRemitConfig.setSecretKey(QK_SECRET_KEY);
		qkRemitConfig.setEventId("withdraw");
		qkRemitConfig.setAppName("qk");
		productionCodeToEventIdCache.put("qk_remit", qkRemitConfig);
		
		APIConfig dxyRemitConfig = new APIConfig();
		dxyRemitConfig.setSecretKey(DXY_SECRET_KEY);
		dxyRemitConfig.setEventId("withdraw");
		dxyRemitConfig.setAppName("dxy");
		productionCodeToEventIdCache.put("dxy_remit", dxyRemitConfig);

	}

	public Map<String, Object> getProductionCodeToEventIdCache() {
		return productionCodeToEventIdCache;
	}

	public void setProductionCodeToEventIdCache(Map<String, Object> productionCodeToEventIdCache) {
		this.productionCodeToEventIdCache = productionCodeToEventIdCache;
	}
}
