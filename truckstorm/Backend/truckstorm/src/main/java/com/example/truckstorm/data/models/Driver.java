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


    @NotBlank(message = "Truck type is required")
    private String truckType;

    @NotNull(message = "Max load capacity is required")
    @Positive(message = "Max load capacity must be positive")
    private Double maxLoadCapacity;

    private boolean available = true;
    private String licenseNumber;
    private Double rating;


    public boolean canCarry(Double weight) {
        return available && maxLoadCapacity >= weight;
    }

}
