package com.example.truckstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.truckstorm.data.repository")
@EntityScan("com.example.truckstorm.data.model")
public class TruckStormApplication {

    public static void main(String[] args) {
        SpringApplication.run(TruckStormApplication.class, args);
    }

}
