package com.zifisense.zetag.http.api.imp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import com.zifisense.zetag.http.api.ZiFiHttpClient;
import com.zifisense.zetag.http.api.model.APIResponse;
import com.zifisense.zetag.http.api.model.ApiConfig;
import com.zifisense.zetag.http.api.model.HttpMethod;
import com.zifisense.zetag.http.api.model.RegionEnum;
import com.zifisense.zetag.http.api.model.ZifisenseApiException;
import com.zifisense.zetag.http.api.util.HMACHelper;


public class ZiFiHttpClientImp<T>  extends ZiFiHttpClient{
	private static Logger logger = LoggerFactory.getLogger(ZiFiHttpClientImp.class);
	
	public ZiFiHttpClientImp(RegionEnum region, String apiKey, String apiSecret) {
		super(region, apiKey, apiSecret);
	}

	@Override
	public APIResponse<T> request(String url, HttpMethod method, Map header, String body) {
		Long t = System.currentTimeMillis();
    	Map<String,String> headers = Maps.newHashMap();
    	headers.put("apiKey", ApiConfig.getApiKey());
		headers.put("sign", HMACHelper.sha256(ApiConfig.getApiSecret(), ApiConfig.getApiKey()+TokenCache.getToken()+t));
		headers.put("t", String.valueOf(t));
		headers.put("Content-Type","application/json;charset=UTF-8");
		if(header != null)
			headers.putAll(header);
		String resStr = RequestHandler.sendRequest(ApiConfig.getUrl()+url, method, headers, body);
		try {
			APIResponse<T> res = JSON.parseObject(resStr,new TypeReference<APIResponse<T>>(){}) ;
            if (!res.isSuccess()) {
                throw new ZifisenseApiException(res.getCode(),res.getMsg());
            }
            return res;
        } catch (Exception e) {
        	logger.error("unknow error",e);
            throw new ZifisenseApiException(e.getMessage());
        }
	}




}
