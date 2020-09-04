package com.zifisense.zetag.http.api;

import com.alibaba.fastjson.JSONObject;
import com.zifisense.zetag.http.api.imp.ZiFiHttpClientImp;
import com.zifisense.zetag.http.api.model.APIResponse;
import com.zifisense.zetag.http.api.model.HttpMethod;
import com.zifisense.zetag.http.api.model.RegionEnum;

public class Main {
	
	public static final String API_KEY = "cf86bc5d68d54932a854a62626c36823";
	public static final String PASSWORD = "3c109b9463504e02bd957cd935f57cae";
	
	public static void main(String[] args) {
		ZiFiHttpClient<JSONObject>  client = new ZiFiHttpClientImp<>(RegionEnum.CN, API_KEY, PASSWORD);
		APIResponse<JSONObject> res = client.request("/v1.0/inventoryCheck/details/7761293443671040?pageNo=1&pageSize=10&checkTime=1598594400000&dataType=1", HttpMethod.GET, null, null);
		System.out.println(res.getResult().toJSONString());
	}
}
