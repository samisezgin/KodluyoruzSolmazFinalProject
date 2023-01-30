package com.samisezgin.finalproject.controller;

import com.samisezgin.finalproject.configuration.RabbitMQSenderConfiguration;
import com.samisezgin.finalproject.exceptions.RoleNotFoundException;
import com.samisezgin.finalproject.model.NotificationRequest;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.repository.RoleRepository;
import com.samisezgin.finalproject.service.impl.SecurityUserDetailsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
public class HelloController {

    private final SecurityUserDetailsService userDetailsManager;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final RabbitTemplate rabbitTemplate;

    public HelloController(SecurityUserDetailsService userDetailsManager, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, RabbitTemplate rabbitTemplate) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpSession session) {
        session.setAttribute(
                "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
        );
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public void addUser(@RequestParam Map<String, String> body) {
        User user = new User();
        user.setEmail(body.get("username"));
        user.setPassword(passwordEncoder.encode(body.get("password")));
        user.setGender(Gender.valueOf(body.get("gender")));
        user.setPassengerType(PassengerType.valueOf(body.get("passenger-type")));
        user.setName(body.get("name"));
        user.setSurname(body.get("surname"));
        user.setPhoneNumber(body.get("phone"));
        user.setRoles(Set.of(roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new RoleNotFoundException("Role Not Found"))));

        rabbitTemplate.convertAndSend(RabbitMQSenderConfiguration.getQueueName(),
                new NotificationRequest("Thank you for creating a new Account in Booking Service." +
                        "Account created successfully with email address: " + user.getEmail(),
                        "MAIL", user.getEmail()));

        userDetailsManager.createUser(user);
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }
        return error;
    }
}
