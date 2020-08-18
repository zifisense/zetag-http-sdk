package com.zifisense.zetag.http.api;

import java.util.Map;

import com.zifisense.zetag.http.api.imp.TokenCache;
import com.zifisense.zetag.http.api.model.APIResponse;
import com.zifisense.zetag.http.api.model.ApiConfig;
import com.zifisense.zetag.http.api.model.HttpMethod;
import com.zifisense.zetag.http.api.model.RegionEnum;

public abstract class ZiFiHttpClient<T> {
	
	
	public ZiFiHttpClient(RegionEnum region, String apiKey, String apiSecret) {
		ApiConfig.putApiKey(apiKey);
		ApiConfig.putApiSecret(apiSecret);
		ApiConfig.putUrl(region.getUrl());
		TokenCache.tokenRefresh();
	}
	
	public abstract APIResponse<T> request(String url, HttpMethod method, Map header, String body);
	
}
