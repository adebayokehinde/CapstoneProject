package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("DRIVER")
@Getter
@Setter
public class Driver extends User {

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Truck type is required")
    @Column(nullable = false)
    private TruckType truckType;

    @NotNull(message = "Max load capacity is required")
    @Positive(message = "Max load capacity must be positive")
    @Column(nullable = false)
    private Double maxLoadCapacity;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean available = true;

    @NotBlank(message = "Driver license number is required")
    @Column(name = "driver_license_number", nullable = false, unique = true)
    private String driverLicenseNumber;

    @Column(columnDefinition = "DECIMAL(3,2) DEFAULT 5.0")
    @PositiveOrZero(message = "Rating must be positive or zero")
    private Double rating = 5.0;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Truck truck;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DriverStatus status = DriverStatus.AVAILABLE;

    @Embedded
    private Location currentLocation;


}
