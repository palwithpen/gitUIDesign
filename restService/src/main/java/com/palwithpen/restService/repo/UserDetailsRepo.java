package com.palwithpen.restService.repo;

import org.springframework.data.repository.CrudRepository;

import com.palwithpen.restService.entity.UserDetails;

public interface UserDetailsRepo extends CrudRepository<UserDetails, String> {

}
