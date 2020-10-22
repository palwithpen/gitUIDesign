package com.palwithpen.restService.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.palwithpen.restService.entity.UserDetails;
import com.palwithpen.restService.entity.UserModel;
import com.palwithpen.restService.repo.UserDetailsRepo;
import com.palwithpen.restService.repo.UserRepo;

@org.springframework.stereotype.Service
public class Service {
	Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired UserRepo userRepo;
	@Autowired UserDetailsRepo detailsRepo;
	
	public Optional<UserModel> getUserByID(String userId) {
		return userRepo.findById(userId);
	}
	
	public void createUser(UserModel userModel){
		logger.info("In service block");
		userRepo.save(userModel);
	}
	public void feedingUserDetails(UserDetails userDetails) {
		detailsRepo.save(userDetails);
	}
	
	public void deleteUser(String id) {
		userRepo.deleteById(id);
	}
}
