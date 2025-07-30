package com.example.truckstorm.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegistrationRequest {
    private String firstName;
    private String Phone;
    private String email;
    private String password;

}
