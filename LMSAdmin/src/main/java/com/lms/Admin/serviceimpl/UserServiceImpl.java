package com.lms.Admin.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.Admin.dto.UserDTO;
import com.lms.Admin.exception.UserAlreadyExistsException;
import com.lms.Admin.exception.UserNotFoundException;
import com.lms.Admin.model.User;
import com.lms.Admin.repository.UserRepository;
import com.lms.Admin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public ResponseEntity<User> addUser(UserDTO userDTO) throws UserAlreadyExistsException {
        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User with username " + userDTO.getUsername() + " already exists");
        }
        
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(String id, UserDTO userDTO) throws UserNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        BeanUtils.copyProperties(userDTO, existingUser);
        userRepository.save(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserById(String userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}