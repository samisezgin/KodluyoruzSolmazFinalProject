package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;

public interface UserService {

    String login(LoginRequest loginRequest);
    void create(UserRequest userRequest);
}
