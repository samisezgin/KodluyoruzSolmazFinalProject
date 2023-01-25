package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.PassengerUser;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.service.UserService;
import com.samisezgin.finalproject.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final String INVALID_EMAIL_OR_PASSWORD = "Email veya şifre yanlış";

    private static final String LOGIN_SUCCESS = "Login Başarılı";
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        PassengerUser passengerUser = modelMapper.map(userRequest,PassengerUser.class);
        passengerUser.setPassword(PasswordUtil.preparePasswordHash(passengerUser.getPassword(),passengerUser.getEmail()));
        userRepository.save(passengerUser);
        return modelMapper.map(passengerUser,UserResponse.class);
    }

    public String login(LoginRequest loginRequest) {

        User foundUser = userRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));

        String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getEmail());

        boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());

        return isValid ? LOGIN_SUCCESS : INVALID_EMAIL_OR_PASSWORD;

    }
}
