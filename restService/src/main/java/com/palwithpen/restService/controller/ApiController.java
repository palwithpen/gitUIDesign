package com.palwithpen.restService.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.palwithpen.restService.bo.ContactDetailsBO;
import com.palwithpen.restService.bo.UserDetailsBO;
import com.palwithpen.restService.entity.UserDetails;
import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.service.Service;
import com.palwithpen.restService.util.ComponentValidator;
import com.palwithpen.restService.util.ResponseGenerator;

@RestController		
public class ApiController {

	Gson gson = new Gson();
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping("/hey")
	@ResponseBody
	public String ConnectivityCheck() {
		return "Connected";
	}
	
	@Autowired Service service;
	@Autowired ComponentValidator comVal;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value= {"/headers"} , method = {RequestMethod.GET})
	public String headerReader(@RequestHeader Map<String, String> headers){
		try {
			Boolean validator = comVal.HeaderValidator(headers);
			if (validator.valueOf(true)) {
				return "header is proper";
			}
			else {
				return "header not good";
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping(value={"/getUser/{userId}"}, method = {RequestMethod.GET})
	public Map<String, Object> getUserById(@PathVariable String userId) {
		Map<String ,Object> responseMap =  new HashMap<>();
		try {
			Optional<UserModel> userDetails = service.getUserByID(userId);
			
			responseMap.put("userName", userDetails.get().getUserId());
			responseMap.put("passKey" , userDetails.get().getPassKey());
			responseMap.put("creationDate",userDetails.get().getCreationDate());
			responseMap.put("userRole",userDetails.get().getUserRole());
			return responseMap;	
			
		}
		catch (Exception e) {
			logger.info("user not found");
			return responseMap;
		}
	}
	
	@RequestMapping(value= {"/createUser"}, method = {RequestMethod.POST})
	public Map<String, Object> createUser(@RequestBody UserModel userModel) {
		LocalDateTime creationDate = LocalDateTime.now();
		DateTimeFormatter formatCdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		userModel.setCreationDate(creationDate.format(formatCdate));
		
		if (userModel.getUserId() != null && userModel.getPassKey() != null && userModel.getUserRole() !=null && !userModel.getUserId().isEmpty() && !userModel.getPassKey().isEmpty() && !userModel.getUserRole().isEmpty()) {
		service.createUser(userModel);
		return ResponseGenerator.getSuccessResponse("user_created_successfully");	
		}
		else {
			return ResponseGenerator.getFailureResponse("data_missing");
		}
	}
	
	
	
	@RequestMapping(value = {"/checkCreds"}, method = {RequestMethod.POST})
	public Map <String, Object> checkUserCreds (@RequestBody Map<String , Object> requestBody, @RequestHeader Map<String , String> headers){
		logger.info("Creds checking 3rd way");
		Map <String, Object> responseMap = new HashMap<String, Object>();
		try {
			if(requestBody.get("userName") != null && !requestBody.get("userName").toString().isEmpty()) {	
				String userIdFetched  = requestBody.get("userName").toString();
				String passKeyFetched = requestBody.get("passKey").toString();
				Map<String, Object> responseUser = getUserById(userIdFetched);
					if (responseUser != null && !responseUser.isEmpty()) {
						
						String userId = responseUser.get("userName").toString();
						String password = responseUser.get("passKey").toString();
						
						if (userIdFetched.equals(userId) && passKeyFetched.equals(password)){
							return ResponseGenerator.getSuccessResponse("user_matched");
						}
						else{
							return ResponseGenerator.getFailureResponse("user_id_or_password_match_failure");
						}
					}
					else {
						return ResponseGenerator.getFailureResponse("user_not_found");
					}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			responseMap.put("Status", "Error");
		}
		return responseMap;
	}
	
	@RequestMapping(value = {"/feedingUserDetails"}, method = {RequestMethod.POST})
	public Map<String, Object> feedingUserDetails(@RequestBody Map<String, Object>requestBody){
		UserDetails payload = new UserDetails();
		ContactDetailsBO contactDetailsBO = new ContactDetailsBO();
		UserDetailsBO userDetailsBO = new UserDetailsBO();
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			
			if (requestBody != null && !requestBody.isEmpty()) {
				LocalDateTime creationDate = LocalDateTime.now();
				DateTimeFormatter formatCdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				
				if(requestBody.get("addressDetails") != null) {
				
					String addressMapper = mapper.writeValueAsString(requestBody.get("addressDetails"));
					JSONObject addJson = new JSONObject(addressMapper);
					logger.info(" " + addJson);
					if(!addJson.getString("iou").isEmpty() && !addJson.getString("functionalUnit").isEmpty() && !addJson.getString("oprationalBranch").isEmpty() && !addJson.getString("postDesignation").isEmpty() && !addJson.getString("reportingPL").isEmpty()) {
						userDetailsBO.setIou(addJson.getString("iou"));
						userDetailsBO.setFuntionalUnit(addJson.getString("functionalUnit"));
						userDetailsBO.setOperationalBranch(addJson.getString("oprationalBranch"));
						userDetailsBO.setPostDesignation(addJson.getString("postDesignation"));
						userDetailsBO.setReportingPL(addJson.getString("reportingPL"));
					}
				}
				else {
					logger.error("addressDetails is null");

					return ResponseGenerator.getFailureResponse("ADDRESS_DETAILS_EMPTY");
				}
				
				if(requestBody.get("contactDetails") != null) {
					String contactMapper = mapper.writeValueAsString(requestBody.get("contactDetails"));
					JSONObject contactJson = new JSONObject(contactMapper);
					logger.info(" " + contactJson);					
					if(!contactJson.getString("extentionNum").isEmpty() && !contactJson.getString("officeEmail").isEmpty() && !contactJson.getString("mobileNum").isEmpty()) {
						contactDetailsBO.setExtNo(contactJson.getString("extentionNum"));
						contactDetailsBO.setOfcEmail(contactJson.getString("officeEmail"));
						contactDetailsBO.setMobileNo(contactJson.getString("mobileNum"));
					}
				}
				else {
					logger.error("contactDetails is null");
					return ResponseGenerator.getFailureResponse("CONTACT_DETAILS_EMPTY");
				}
				String contactDetailsPayload = gson.toJson(contactDetailsBO);
				String userDetailsPayload = gson.toJson(userDetailsBO);
				
				payload.setCreationDate(creationDate.format(formatCdate));
				payload.setUserId(requestBody.get("userId").toString());
				
				logger.info("Contact Details "+contactDetailsPayload);
				logger.info("User Details "+userDetailsPayload);
				
				payload.setContactDetails(contactDetailsPayload);
				payload.setUserDetails(userDetailsPayload);

				service.feedingUserDetails(payload);
				return ResponseGenerator.getSuccessResponse("DETAILS_ADDED_SUCCESSFULLY");
			}
			else {
				return ResponseGenerator.getFailureResponse("REQUEST_EMPTY");
			}

		}
		catch(Exception ex) {
			logger.error("Error in User Details " + ex);
			return ResponseGenerator.getExcpResponse(ex);
		}
	}
}
	
