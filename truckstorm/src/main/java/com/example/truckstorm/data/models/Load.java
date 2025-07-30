package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "loads")
public class Load {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Delivery location is required")
    private String deliveryLocation;

    @NotNull(message = "Weight is required")
    @Positive(message = "Weight must be positive")
    private Double weight;
    @NotBlank(message = "Load type is required")
    private LoadType loadType;
    private int clientId;

    @NotNull(message = "image is required")
    private List<String> mediaUrls;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LoadStatus loadStatus;
    private String note;



}
