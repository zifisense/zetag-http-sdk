package com.zifisense.zetag.http.api.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class ApiConfig {
	private ApiConfig() {}
	
	private static final String API_KEY = "apiKey";
	private static final String API_SECRET = "apiSecret";
	private static final String URL = "url";
	private static final Map<String,String> configs = Maps.newHashMap();
	
	public static void putUrl(String value) {
		configs.put(URL, value);
	}
	
	public static String getUrl() {
		return configs.get(URL);
	}
	
	public static void putApiKey(String value) {
		configs.put(API_KEY, value);
	}
	
	public static String getApiKey() {
		return configs.get(API_KEY);
	}
	
	public static void putApiSecret(String value) {
		configs.put(API_SECRET, value);
	}
	
	public static String getApiSecret() {
		return configs.get(API_SECRET);
	}
}
