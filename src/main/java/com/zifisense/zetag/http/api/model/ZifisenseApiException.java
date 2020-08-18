package com.zifisense.zetag.http.api.model;
/**
 * <p>Title: ZetagApiException</p>  
 * <p>Description: </p>  
 * <p>Company: zifisence</p>   
 * @author chensl  
 * @date 2020年7月28日 下午5:06:09
 */
public class ZifisenseApiException extends RuntimeException {
	
	public ZifisenseApiException() {
		
	}
	
public ZifisenseApiException(String msg) {
	super(msg);
	this.code = "1000";
	this.msg = msg;
	}
	
	public ZifisenseApiException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
