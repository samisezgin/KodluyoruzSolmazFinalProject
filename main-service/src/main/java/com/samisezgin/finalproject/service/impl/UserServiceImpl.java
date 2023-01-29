package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.NotificationRequest;
import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.enums.RoleName;
import com.samisezgin.finalproject.repository.RoleRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.service.UserService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@Service
public class UserServiceImpl implements UserService {
    private static final String INVALID_EMAIL_OR_PASSWORD = "Email veya şifre yanlış";

    private static final String LOGIN_SUCCESS = "Login Başarılı";
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RabbitTemplate template;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RabbitTemplate template,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.template = template;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User passengerUser = modelMapper.map(userRequest, User.class);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("USER").orElseThrow(() -> new RuntimeException("Role is not exist")));
        passengerUser.setRoles(roles);
        //passengerUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        //passengerUser.setPassword(PasswordUtil.preparePasswordHash(passengerUser.getPassword(),passengerUser.getEmail()));
        userRepository.save(passengerUser);
        LoggerUtil.getLogger().log(Level.INFO, "UserService -> createUser : " + userRequest.getEmail());
        template.convertAndSend("notification", new NotificationRequest("User successfully created with email: " + userRequest.getEmail(), "EMAIL", userRequest.getEmail()));

        return modelMapper.map(passengerUser, UserResponse.class);
    }


    @Override
    public List<UserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    public String login(LoginRequest loginRequest) {

        User foundUser = userRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));

        //String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getEmail());
        String passwordHash=passwordEncoder.encode(loginRequest.getPassword());
        boolean isValid = true;//PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());
        LoggerUtil.getLogger().log(Level.INFO, "UserService -> userLogin : " + loginRequest.getEmail() + (isValid ? " success." : " failed."));
        return isValid ? LOGIN_SUCCESS : INVALID_EMAIL_OR_PASSWORD;

    }
@Override
public User findByEmail(String email)
{
    return userRepository.findByEmailIgnoreCase(email).orElseThrow(()->new UserNotFoundException("User not found by given email."));
}

}
