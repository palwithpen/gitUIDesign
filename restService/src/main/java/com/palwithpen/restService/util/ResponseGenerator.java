package com.palwithpen.restService.util;

import java.util.HashMap;
import java.util.Map;


public class ResponseGenerator {

	public static Map<String, Object> getSuccessResponse(String description) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("statusCode", "000");
		responseMap.put("statusDescription", description);
		responseMap.put("statusType", "SUCCESS");
		return responseMap;
	}
	
	public static Map<String, Object> getFailureResponse(String remark){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("statusCode", "1099");
		responseMap.put("statusDescription", remark);
		responseMap.put("statusType", "FAILURE");
		
		return responseMap;
	}
}
