package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.DriverStatus;
import com.example.truckstorm.data.models.Truck;
import com.example.truckstorm.data.models.TruckType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class DriverRegistrationRequest {



    @NotNull(message = "Max load capacity is required")
    @Positive(message = "Max load capacity must be positive")
    @Column(nullable = false)
    private Double maxLoadCapacity;

    private DriverStatus driverStatus;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean available = true;

    private LocalDateTime updatedAt;

    @NotBlank(message = "Driver license number is required")
    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;

    private String TruckLicenseNumber;
    private String PlateNumber;
    private Truck truck;
    private TruckType truckType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.AVAILABLE;

    @Embedded
    private String currentLocation;

    @NotNull
    private String imageUrl;
    @NotNull
    private String profileImageUrl;

}
