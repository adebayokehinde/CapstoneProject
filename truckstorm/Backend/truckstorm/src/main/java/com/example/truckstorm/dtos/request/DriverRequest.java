package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.TruckType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverRequest {
    @NotNull
    private TruckType truckType;

    @NotNull @Positive
    private Double maxLoadCapacity;

    @NotBlank
    private String driverLicenseNumber;

    private String currentLocation;
}
