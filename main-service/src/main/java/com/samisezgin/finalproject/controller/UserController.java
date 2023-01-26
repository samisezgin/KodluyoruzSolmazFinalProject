package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest)
    {
       return userService.create(userRequest);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping
    public List<UserResponse> getAll()
    {
        return userService.getAll();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}
