package com.palwithpen.restService.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.palwithpen.restService.bo.PostSkeleton;
import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.service.Service;

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
	
	@RequestMapping(value={"/getUser/{userId}"}, method = {RequestMethod.GET})
	public Object getUserById(@PathVariable String userId) {
		logger.info("Checking Credentials for " + userId);
		Object userDetails = service.getUserByID(userId);
		return userDetails;	
	}
	
	@RequestMapping(value= {"/createUser"}, method = {RequestMethod.POST})
	public String createUser(@RequestBody UserModel userModel) {
		LocalDateTime creationDate = LocalDateTime.now();
		DateTimeFormatter formatCdate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		userModel.setCreationDate(creationDate.format(formatCdate));
		logger.info(userModel.getCreationDate() + " "+ userModel.getUserId() + "" + userModel.getPassKey()+ " "+ userModel.getUserRole());
		
		if (userModel.getUserId() != null && userModel.getPassKey() != null && userModel.getUserRole() !=null && !userModel.getUserId().isEmpty() && !userModel.getPassKey().isEmpty() && !userModel.getUserRole().isEmpty()) {
		service.createUser(userModel);
		return "User Created Successfully";	
		}
		else {
			return "Something is missing";
		}
	}
	
	@RequestMapping(value= {"/hitPost"}, method = {RequestMethod.POST} )
	public Map<String, Object> hitPost(@RequestBody PostSkeleton postBo) {
		Map <String, Object> responseMap= new HashMap<>(); 
		
		return responseMap;
	}
	
	@RequestMapping(value = {"/checkCreds"}, method = {RequestMethod.POST})
	public Map <String, Object> checkUserCreds (@RequestBody Map<String , Object> requestBody){
		Map <String, Object> responseMap = new HashMap<String, Object>();
		try {

			if(requestBody.get("userName") != null && !requestBody.get("userName").toString().isEmpty()) {
				String userId = requestBody.get("userName").toString();
			Object response = getUserById(userId);
			String responseJson = gson.toJson(response);
			logger.info("getting response as json " + responseJson);
			
			}
			responseMap.put("statusCode","0000");
			responseMap.put("statusDescription", "user found");
		}
		catch(Exception e) {
			e.printStackTrace();

		      responseMap.put("Status", "Error");
		}
		return responseMap;
	}
	
}
	
