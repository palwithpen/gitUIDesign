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
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.palwithpen.restService.bo.PostSkeleton;
import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.service.Service;
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
	
	
	@RequestMapping(value={"/getUser/{userId}"}, method = {RequestMethod.GET})
	public Map<String, String> getUserById(@PathVariable String userId) {
		Map<String ,String> responseMap =  new HashMap<>();
		Optional<UserModel> userDetails = service.getUserByID(userId);
		responseMap.put("userName", userDetails.get().getUserId());
		responseMap.put("passKey" , userDetails.get().getPassKey());
		responseMap.put("creationDate",userDetails.get().getCreationDate());
		responseMap.put("userRole",userDetails.get().getUserRole());
		return responseMap;	
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
	
	
	
	@RequestMapping(value = {"/checkCreds"}, method = {RequestMethod.POST})
	public Map <String, Object> checkUserCreds (@RequestBody Map<String , Object> requestBody){
		logger.info("Creds checking 3rd way");
		Map <String, Object> responseMap = new HashMap<String, Object>();
		try {
			
			if(requestBody.get("userName") != null && !requestBody.get("userName").toString().isEmpty()) {
				String userIdFetched  = requestBody.get("userName").toString();
				String passKeyFetched = requestBody.get("passKey").toString();
				
				Map<String, String> responseUser = getUserById(userIdFetched);
				String userId = responseUser.get("userName").toString();
				String passKey = responseUser.get("passKey").toString();
				
				try {

					if (responseUser != null && !responseUser.isEmpty()) {
						logger.info(passKey);
						logger.info(passKeyFetched);
						if (passKey.trim().toString() == passKeyFetched.trim().toString()) {
							logger.info("sjadghj");
						}
					}
					else {
						responseMap.put("status", "user_not_found");
					}	
				}
				catch(Exception e) {
					logger.error(" "+e);
				}
			}
		responseMap.put("jsdh", "jsd");
		}
		catch(Exception e) {
			e.printStackTrace();

		      responseMap.put("Status", "Error");
		}

		return null;
				}
	
}
	
