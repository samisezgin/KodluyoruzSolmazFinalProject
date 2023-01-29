package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.exceptions.RoleNotFoundException;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.NotificationRequest;
import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.repository.RoleRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.service.UserService;
import com.samisezgin.finalproject.util.LoggerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RabbitTemplate template;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RabbitTemplate template,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.template = template;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User passengerUser = modelMapper.map(userRequest, User.class);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("USER").orElseThrow(() -> new RoleNotFoundException("Role is not exist")));
        passengerUser.setRoles(roles);
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

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new UserNotFoundException("User not found by given email."));
    }

}
