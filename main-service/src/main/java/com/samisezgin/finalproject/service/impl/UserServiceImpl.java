package com.samisezgin.finalproject.service.impl;

import com.samisezgin.finalproject.dto.request.LoginRequest;
import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.Notification;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.enums.Role;
import com.samisezgin.finalproject.repository.UserRepository;
import com.samisezgin.finalproject.service.UserService;
import com.samisezgin.finalproject.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String INVALID_EMAIL_OR_PASSWORD = "Email veya şifre yanlış";

    private static final String LOGIN_SUCCESS = "Login Başarılı";
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final RabbitTemplate template;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RabbitTemplate template) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.template = template;
    }
    @Override
    public UserResponse create(UserRequest userRequest) {
        User passengerUser = modelMapper.map(userRequest,User.class);
        passengerUser.setRole(Role.USER);
        passengerUser.setPassword(PasswordUtil.preparePasswordHash(passengerUser.getPassword(),passengerUser.getEmail()));
        userRepository.save(passengerUser);
        template.convertAndSend("notification", new Notification("Kullanıcı başarılı bir şekilde oluşturulmuştur. "+userRequest.getEmail(), "EMAIL",userRequest.getEmail()));
        return modelMapper.map(passengerUser,UserResponse.class);
    }
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user,UserResponse.class)).toList();
    }

    public String login(LoginRequest loginRequest) {

        User foundUser = userRepository.findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("kullanıcı bulunamadı"));

        String passwordHash = PasswordUtil.preparePasswordHash(loginRequest.getPassword(), loginRequest.getEmail());

        boolean isValid = PasswordUtil.validatePassword(passwordHash, foundUser.getPassword());

        return isValid ? LOGIN_SUCCESS : INVALID_EMAIL_OR_PASSWORD;

    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(username);
//        if (!optionalUser.isPresent()) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        User user = optionalUser.get();
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
//    }
}
