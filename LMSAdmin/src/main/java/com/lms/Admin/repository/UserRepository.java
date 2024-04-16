package com.lms.Admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.Admin.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}
