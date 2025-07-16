package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignDriverRequestDTO {
    @NotNull(message = "Load ID cannot be null")
    private Long loadId;

    @NotNull(message = "Driver ID cannot be null")
    private Long driverId;
}
