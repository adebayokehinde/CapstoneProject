package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.DriverStatus;
import com.example.truckstorm.data.models.Truck;
import com.example.truckstorm.data.models.TruckType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverRegistrationRequest {
    @NotNull
    private String firstName;

    @Email
    private String email;
    @NotBlank
    private String Password;
    @NotBlank(message = "Driver license number is required")
    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;
    private boolean ownsTruck;
    private TruckDTO truckDetails;
    private String profileImageUrl;

}
