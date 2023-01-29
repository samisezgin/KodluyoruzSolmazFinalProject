package com.samisezgin.finalproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Booking Planner API", version = "0.1", description = "Booking Planner Main API Service"))
public class KodluyoruzSolmazFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KodluyoruzSolmazFinalProjectApplication.class, args);
    }

}
