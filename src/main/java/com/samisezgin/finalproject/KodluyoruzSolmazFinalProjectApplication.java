package com.samisezgin.finalproject;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "techgeeknext-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class KodluyoruzSolmazFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodluyoruzSolmazFinalProjectApplication.class, args);
    }

}
