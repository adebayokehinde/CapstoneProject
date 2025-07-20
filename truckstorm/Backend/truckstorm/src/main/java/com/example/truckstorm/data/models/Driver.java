package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("DRIVER")
@Getter
@Setter
public class Driver extends User {

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Truck type is required")
    private TruckType truckType;


    @Positive(message = "Max load capacity must be positive")
    private Double maxLoadCapacity;

    private DriverStatus driverStatus;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean available = true;

    private LocalDateTime updatedAt;

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
    private String currentLocation; // Could be replaced with GeoJSON later

    @NotNull
    private String profileImageUrl;

    @OneToOne(mappedBy = "driver")
    private Truck assignedTruck;

    @OneToMany(mappedBy = "owner")
    private List<Truck> ownedTrucks;




}
