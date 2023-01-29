package com.samisezgin.finalproject.service.impl;


import com.samisezgin.finalproject.dto.request.UserRequest;
import com.samisezgin.finalproject.dto.response.UserResponse;
import com.samisezgin.finalproject.exceptions.UserNotFoundException;
import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.repository.RoleRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;



@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void createTest() {
        // Prepare the input
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("test@email.com");

        // Prepare the output
        User passengerUser = new User();
        passengerUser.setEmail("test@email.com");
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName("USER");
        roles.add(role);
        passengerUser.setRoles(roles);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail("test@email.com");

        // Prepare the mocks
        when(modelMapper.map(userRequest, User.class)).thenReturn(passengerUser);
        when(roleRepository.findByRoleName("USER")).thenReturn(Optional.of(role));
        when(modelMapper.map(passengerUser, UserResponse.class)).thenReturn(userResponse);
    }

    @Test
    public void getAllTest() {
        // Prepare the input
        User user1 = new User();
        user1.setEmail("user1@email.com");
        User user2 = new User();
        user2.setEmail("user2@email.com");
        List<User> userList = Arrays.asList(user1, user2);

        // Prepare the output
        UserResponse userResponse1 = new UserResponse();
        userResponse1.setEmail("user1@email.com");
        UserResponse userResponse2 = new UserResponse();
        userResponse2.setEmail("user2@email.com");
        List<UserResponse> userResponseList = Arrays.asList(userResponse1, userResponse2);

        // Prepare the mocks
        when(userRepository.findAll()).thenReturn(userList);
        when(modelMapper.map(user1, UserResponse.class)).thenReturn(userResponse1);
        when(modelMapper.map(user2, UserResponse.class)).thenReturn(userResponse2);

        // Call the method under test
        List<UserResponse> result = userService.getAll();

        // Verify the results
        assertEquals(userResponseList, result);
        verify(userRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(user1, UserResponse.class);
        verify(modelMapper, times(1)).map(user2, UserResponse.class);
    }

    @Test
    public void findByEmailTest() {
        // Prepare the input
        String email = "user1@email.com";

        // Prepare the output
        User user = new User();
        user.setEmail(email);
        Optional<User> userOpt = Optional.of(user);

        // Prepare the mocks
        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(userOpt);

        // Call the method under test
        User result = userService.findByEmail(email);

        // Verify the results
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmailIgnoreCase(email);
    }

    @Test
    public void findByEmailTest_UserNotFound() {
        // Prepare the input
        String email = "user1@email.com";

        // Prepare the mocks
        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        // Call the method under test
        try {
            userService.findByEmail(email);
            fail("Expected UserNotFoundException to be thrown.");
        } catch (UserNotFoundException e) {
            assertEquals("User not found by given email.", e.getMessage());
        }
        // Verify the results
        verify(userRepository, times(1)).findByEmailIgnoreCase(email);
    }

}