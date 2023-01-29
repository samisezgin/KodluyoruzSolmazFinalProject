package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.model.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;

public interface UserService  {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAll();

    User findByEmail(String email);
}
