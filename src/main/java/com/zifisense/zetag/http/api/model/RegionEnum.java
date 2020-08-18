package com.zifisense.zetag.http.api.model;

public enum RegionEnum {
	
	CN("http://192.168.0.26:20008");
	
	RegionEnum(String url) {
		this.url = url;
	}
	
	private String url;

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}	
