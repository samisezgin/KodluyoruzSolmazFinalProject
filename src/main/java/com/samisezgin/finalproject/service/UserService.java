package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;

public interface UserService {

    String login(LoginRequest loginRequest);
    UserResponse create(UserRequest userRequest);
}
