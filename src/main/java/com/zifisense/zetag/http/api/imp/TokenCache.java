package com.zifisense.zetag.http.api.imp;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.zifisense.zetag.http.api.model.APIResponse;
import com.zifisense.zetag.http.api.model.ApiConfig;
import com.zifisense.zetag.http.api.model.HttpMethod;
import com.zifisense.zetag.http.api.model.TokenResult;
import com.zifisense.zetag.http.api.model.ZifisenseApiException;
import com.zifisense.zetag.http.api.util.HMACHelper;

public class TokenCache {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String EXPIRE_TIME = "expire_time";

    

    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .concurrencyLevel(10)
            .recordStats()
            .build();
    
    public static void tokenRefresh() {
    	Long t = System.currentTimeMillis();
    	Map<String,String> headers = Maps.newHashMap();
		headers.put("apiKey", ApiConfig.getApiKey());
		headers.put("sign", HMACHelper.sha256(ApiConfig.getApiSecret(), ApiConfig.getApiKey()+t));
		headers.put("t", t+"");
		headers.put("Content-Type","application/json;charset=UTF-8");
		String body = RequestHandler.sendRequest(ApiConfig.getUrl()+"/v1.0/token", HttpMethod.GET, headers, null);
		APIResponse<TokenResult> result = JSON.parseObject(body,new TypeReference<APIResponse<TokenResult>>(){}) ;
		if(!result.isSuccess()) {
			throw new ZifisenseApiException(result.getCode()+ "|" + result.getMsg());
		}
		setToken(result.getResult());
    }
    
    /**
     * 从缓存获取token
     *
     * @return
     */
    public static synchronized String getToken() {
        String accessToken = cache.getIfPresent(ACCESS_TOKEN);
        if (accessToken == null || accessToken.length() == 0) {
            try {
                // 缓存命中，过期时间在30s后，直接返回，否则需要刷新
                long expireTime = Long.valueOf(cache.getIfPresent(EXPIRE_TIME));
                if (expireTime > (System.currentTimeMillis()/1000 + 30)) {
                    return accessToken;
                }
                tokenRefresh();
            } catch (Exception e) {
                // 刷新失败，重新获取
            	tokenRefresh();
            }
        } else {
            // 未命中缓存，获取token
        	tokenRefresh();
        }
        return cache.getIfPresent(ACCESS_TOKEN);
    }


    public static synchronized void setToken(TokenResult tr) {
        if (null != tr ) {
            cache.put(ACCESS_TOKEN, tr.getAccessToken());
            cache.put(REFRESH_TOKEN, tr.getRefreshToken());
            cache.put(EXPIRE_TIME, String.valueOf(System.currentTimeMillis()/1000 + tr.getExpire()));
        }
    }

}
