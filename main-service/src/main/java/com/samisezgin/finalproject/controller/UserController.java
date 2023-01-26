package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.service.UserService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "UserController POST request -> create :" + userRequest.getEmail());
        return userService.create(userRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        LoggerUtil.getLogger().log(Level.INFO, "UserController POST request -> login :" + loginRequest.getEmail());
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

}
