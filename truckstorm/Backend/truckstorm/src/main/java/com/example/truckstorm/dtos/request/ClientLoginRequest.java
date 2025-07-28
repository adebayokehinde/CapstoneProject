package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientLoginRequest {
    @NotBlank(message = "email Should not be blank")
    private String email;
    @NotBlank(message = "field is required")
    private String password;
}
