package com.palwithpen.restService.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.service.Service;

@RestController		
public class ApiController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
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
		
		service.createUser(userModel);
		
		return "User Created Successfully";	
	}	
}
	
