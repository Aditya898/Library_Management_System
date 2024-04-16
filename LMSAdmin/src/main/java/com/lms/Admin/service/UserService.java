package com.lms.Admin.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.Admin.dto.UserDTO;
import com.lms.Admin.model.User;

public interface UserService {
    ResponseEntity<User> addUser(UserDTO userDTO);
    ResponseEntity<User> updateUser(String id, UserDTO userDTO);
    ResponseEntity<Void> deleteUser(String id);
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserByUsername(String username);
    ResponseEntity<User> getUserByEmail(String email);
    ResponseEntity<User> getUserById(String userId);
}
