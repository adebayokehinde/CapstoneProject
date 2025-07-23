package com.example.truckstorm.dtos.request;

import lombok.Data;

@Data
public class DriverLoginRequest {
    private String email;
    private String password;
}
