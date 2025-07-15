package com.example.truckstorm.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Delivery location is required")
    private String deliveryLocation;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    @NotBlank(message = "Load type is required")
    private String loadType;
    private String clientId;

    public Load(String pickupLocation, String deliveryLocation, Double weight, String loadType, String clientId) {
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.weight = weight;
        this.loadType = loadType;
        this.clientId = clientId;

    }

    public void setPickupLocation(String semicolonYabaLagos) {
    }
}
