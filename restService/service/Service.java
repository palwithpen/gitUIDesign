package com.palwithpen.restService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.repo.UserRepo;

@org.springframework.stereotype.Service
public class Service {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired UserRepo userRepo;
	
	public Object getUserByID(String userId) {
		Object userDetails = userRepo.findById(userId);
		logger.info(userId + "'s user found");
		return userDetails;
	}
	
	public void createUser(UserModel userModel){
		logger.info("In service block");
		userRepo.save(userModel);
	}
}
