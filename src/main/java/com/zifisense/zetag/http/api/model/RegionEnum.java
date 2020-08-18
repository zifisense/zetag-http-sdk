package com.zifisense.zetag.http.api.model;

public enum RegionEnum {
	
	CN("https://zetagos.zifisense.com:20008");
	
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
