package com.palwithpen.restService.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ComponentValidator {

	@Value("${auth.header.value}")
	private String headerValue;
	
	@Value("${auth.header.key}")
	private String headerKey;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public Boolean HeaderValidator(Map<String, String> headers) {
		Boolean res = null; 
		try {
			if(headers != null && !headers.isEmpty() && headers.containsKey("username") && headers.containsKey("passPhrase")) {
				String head1 = headers.get("username");
				String head2 = headers.get("passPhrase");	
				if (head1.equals(headerKey) && head2.equals(headerValue)) {
					logger.info("Got proper headers");
					res = true;
				}
				else {
					res = false;
				}
				
			}
			else {
				res = false;
				logger.error("headers is/are not proper");
			}
			
		} catch (Exception e) {
			logger.error("Exception in header validation " + e.getMessage());
			return res =  false;
		}
		return res;
	}
}
