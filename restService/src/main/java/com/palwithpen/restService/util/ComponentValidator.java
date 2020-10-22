package com.palwithpen.restService.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ComponentValidator {

	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public Boolean HeaderValidator(Map<String, String> headers) {
		Boolean res = null; 
		try {
			if(headers != null && !headers.isEmpty() && headers.containsKey("passphrase") && headers.containsKey("username")) {
				logger.info("inside if block");
				String head1 = headers.get("username");
				String head2 = headers.get("passphrase");
				if (head1.equals("palwithpen") && head2.equals("proto")) {
					logger.info("Got proper headers");
					res = true;
				}else {
					logger.info("headers didn't match");
					res = false;
				}
			}else {
				res = false;
				logger.error("headers is/are not proper");
			}
		} catch (Exception e) {
			logger.error("Exception in header validation " + e.getMessage());
			res =  false;
			}
		return res;
	}
}
