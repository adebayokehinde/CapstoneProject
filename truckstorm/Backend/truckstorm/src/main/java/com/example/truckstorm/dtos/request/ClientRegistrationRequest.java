package com.example.truckstorm.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientRegistrationRequest {
    private String firstName;
    private String Phone;
    private String email;
    private String password;

}
