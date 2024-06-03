package com.zifisense.zetag.http.api.model;
/**
 * 
 * <p>Title: APIResponse</p>  
 * <p>Description: </p>  
 * <p>Company: zifisence</p>   
 * @author chensl  
 * @date 2020年7月28日 下午2:25:11
 */
public class APIResponse<T> {
	
	public APIResponse() {}
	
	public APIResponse(boolean success,String code,String msg) {
		this.success = success;
		this.code = code;
		this.msg = msg;
	}
	
	private boolean success = true;
	
	private String code;
	
	private String msg;
	
	private T result;

	private String requestId;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
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
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
