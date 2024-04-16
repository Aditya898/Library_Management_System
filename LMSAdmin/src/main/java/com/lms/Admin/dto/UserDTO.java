package com.lms.Admin.dto;

import com.lms.Admin.model.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private UserType userType;

    
}
