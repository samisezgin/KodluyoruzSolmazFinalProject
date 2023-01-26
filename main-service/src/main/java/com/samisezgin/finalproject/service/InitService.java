package com.samisezgin.finalproject.service;

import com.samisezgin.finalproject.model.Address;
import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.User;
import com.samisezgin.finalproject.model.enums.Gender;
import com.samisezgin.finalproject.model.enums.PassengerType;
import com.samisezgin.finalproject.model.enums.RoleName;
import com.samisezgin.finalproject.repository.RoleRepository;
import com.samisezgin.finalproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void addRoles()
    {
        if(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).isEmpty()){
            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        }
        if(roleRepository.findByRoleName(RoleName.ROLE_USER).isEmpty()){
            roleRepository.save(new Role(RoleName.ROLE_USER));
        }

//        User admin=new User();
//        admin.setBookingList(new ArrayList<>());
//        admin.setAddress(new Address());
//        admin.setPassengerType(PassengerType.CORPORATE);
//        admin.setName("admin");
//        admin.setEmail("admin");
//        admin.setSurname("admin");
//        admin.setPassword("admin");
//        admin.setPhoneNumber("12345678910");
//        admin.setGender(Gender.MALE);
//
//        Set<Role> roles=new HashSet<>();
//        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(()->new RuntimeException("Role is not exist")));
//        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(()->new RuntimeException("Role is not exist")));
//
//        admin.setRoles(roles);
//        userRepository.save(admin);
//
//        User user=new User();
//        user.setName("user");
//        user.setEmail("user");
//        user.setSurname("user");
//        user.setPassword("user");
//        user.setPhoneNumber("12345678910");
//        user.setGender(Gender.MALE);
//        user.setRoles(Set.of(roleRepository.findByRoleName(RoleName.ROLE_USER).get()));
//        userRepository.save(user);

    }
    private InitService(RoleRepository roleRepository,
                        UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
}
