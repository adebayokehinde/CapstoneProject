package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignDriverRequestDTO {
    @NotNull(message = "Load ID cannot be null")
    private Long loadId;

    @NotNull(message = "Driver ID cannot be null")
    private Long driverId;
}
