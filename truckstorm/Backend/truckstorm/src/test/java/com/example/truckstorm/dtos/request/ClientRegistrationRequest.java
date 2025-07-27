package com.example.truckstorm.dtos.request;

import lombok.Data;

@Data
public class ClientRegistrationRequest {
    private String Phone;
    private String email;
    private String password;

}
