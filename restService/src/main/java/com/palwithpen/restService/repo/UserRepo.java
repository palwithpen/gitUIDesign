package com.palwithpen.restService.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.palwithpen.restService.entity.UserModel;

@Repository
public interface UserRepo extends CrudRepository<UserModel, String> {


}
