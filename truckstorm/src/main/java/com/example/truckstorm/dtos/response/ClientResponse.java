package com.example.truckstorm.dtos.response;

import com.example.truckstorm.data.models.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private int id;
    private String message;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
}
