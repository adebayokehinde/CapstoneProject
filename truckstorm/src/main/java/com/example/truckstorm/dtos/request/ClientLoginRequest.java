package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoginRequest {
    @NotBlank(message = "email Should not be blank")
    private String email;
    @NotBlank(message = "field is required")
    private String password;
}
