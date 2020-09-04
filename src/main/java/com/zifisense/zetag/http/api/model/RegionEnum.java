package com.zifisense.zetag.http.api.model;

public enum RegionEnum {
	
	CN("https://120.27.246.24");
	
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
