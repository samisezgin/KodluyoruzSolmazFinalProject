package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.model.User;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAll();

    User findByEmail(String email);
}
