package com.zifisense.zetag.http.api.imp;

import java.util.Map;

import com.github.kevinsawicki.http.HttpRequest;
import com.zifisense.zetag.http.api.model.ApiConfig;
import com.zifisense.zetag.http.api.model.HttpMethod;
import com.zifisense.zetag.http.api.model.ZifisenseApiException;

/**
 * 执行
 * <p>Title: RequestHandler</p>  
 * <p>Description: </p>  
 * <p>Company: zifisence</p>   
 * @author chensl  
 * @date 2020年8月18日 下午4:12:58
 */
public class RequestHandler {
	 /**
     * 读超时时间（秒）
     */
    private static final int readTimeout = 30;
    /**
     * 连接超时时间（秒）
     */
    private static final int connTimeout = 30;

    /**
     * 重试次数
     */
    private static final int maxRetry = 3;

    /**
     * 执行请求, token过期自动重试
     *
     * @param ar
     * @param withToken
     * @return
     */
    public static String sendRequest(String url, HttpMethod method, Map headers, String body) {
        String result = null;
        int retry = RequestHandler.maxRetry;
        boolean retryFlag = Boolean.TRUE;

        while (retry >= 0 && retryFlag) {
            try {
                result = execute(url, method, headers, body);
                retryFlag = Boolean.FALSE;
            } catch (ZifisenseApiException e) {
                // token无效，重新获取token
                if (("1005".equals(e.getCode()) || "1007".equals(e.getCode())) && retry > 0) {
                    retry--;
                    TokenCache.tokenRefresh();
                } else {
                    throw e;
                }
            }
        }

        return result;
    }
    
    /**
     * 执行请求
     *
     * @param ar 请求实体
     * @param withToken 是否需要token
     * @return
     */
    private static String execute(String url, HttpMethod method, Map headers, String body) {
    	String resStr = null;
    	HttpRequest request =  HttpRequest.get(url).headers(headers)
    			.connectTimeout(connTimeout*1000)
    			.readTimeout(readTimeout*1000)
    			;
		if(HttpMethod.GET == method) {
			resStr = request.body();
		}else if(HttpMethod.POST == method) {
			resStr = request.send(body == null ? "":body).body();
		}else if(HttpMethod.DELETE == method) {
			resStr = request.body();
		}else if(HttpMethod.PUT == method) {
			resStr = request.send(body == null ? "":body).body();
		}else {
			throw new ZifisenseApiException("Method only support GET, POST, PUT, DELETE");
		}
		return resStr;
    }
    
}
