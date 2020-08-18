package com.zifisense.zetag.http.api.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HMAC加密工具类
 * <p>Title: HMACHelper</p>  
 * <p>Description: </p>  
 * <p>Company: zifisence</p>   
 * @author chensl  
 * @date 2020年7月27日 下午4:16:08
 */
public class HMACHelper {	
	
	public static final String HMACSHA256 = "HmacSHA256";
	private static Logger logger = LoggerFactory.getLogger(HMACHelper.class);
	/**
	 * 
	 * @param secret
	 * @param message
	 * @return
	 */
	public static String sha256(String secret,String message) {
		try {
			Mac sha256HMAC = Mac.getInstance(HMACSHA256);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), HMACSHA256);
			sha256HMAC.init(secret_key);
			byte[] bytes = sha256HMAC.doFinal(message.getBytes());
			return new HexBinaryAdapter().marshal(bytes).toUpperCase();
		} catch (Exception e){
			logger.error("HMAC ERROR",e);
			return null;
		}
	}
	
}
