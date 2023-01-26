package com.samisezgin.finalproject;

import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.enums.RoleName;
import com.samisezgin.finalproject.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class KodluyoruzSolmazFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodluyoruzSolmazFinalProjectApplication.class, args);
    }

}
