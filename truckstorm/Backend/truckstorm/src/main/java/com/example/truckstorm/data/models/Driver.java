package com.example.truckstorm.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Current location is required")
    private String currentLocation;

    @NotBlank(message = "Truck type is required")
    private String truckType;

    @NotNull(message = "Max load capacity is required")
    @Positive(message = "Max load capacity must be positive")
    private Double maxLoadCapacity;

    private boolean available = true;

    private String licenseNumber;
    private String phoneNumber;
    private Double rating;

    public Driver(String name, String currentLocation, String truckType, Double maxLoadCapacity) {
        this.name = name;
        this.currentLocation = currentLocation;
        this.truckType = truckType;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public boolean canCarry(Double weight) {
        return available && maxLoadCapacity >= weight;
    }

}
