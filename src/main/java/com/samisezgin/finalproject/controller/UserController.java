package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void create(@RequestBody UserRequest userRequest)
    {
       userService.create(userRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
