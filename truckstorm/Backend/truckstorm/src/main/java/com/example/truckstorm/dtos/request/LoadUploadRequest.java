package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.models.LoadType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoadUploadRequest {
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Delivery location is required")
    private String deliveryLocation;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    @NotBlank(message = "Load type is required")
    private LoadType loadType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private LoadStatus loadStatus;
    private Long clientId;

}
