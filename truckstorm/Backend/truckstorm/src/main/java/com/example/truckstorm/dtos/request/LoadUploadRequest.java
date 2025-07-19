package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.models.LoadType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Length;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class LoadUploadRequest {
    private int id;
    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Delivery location is required")
    private String deliveryLocation;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    @NotNull(message = "Load type is required")
    @Enumerated(EnumType.STRING)
    private LoadType loadType;

    @NotBlank
    @NotNull(message = "image is required")
    private List<String> imageUrls;

    private int clientId;
    private String note;

}
