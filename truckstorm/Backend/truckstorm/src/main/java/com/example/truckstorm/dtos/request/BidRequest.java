package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
public class BidRequest {
    @NotNull
    private BigDecimal price;

    @NotNull
    private int loadId;

    @NotNull
    private int driverId;
    private double weight;
    private int clientId;
    private String pickUpLocation;
    private String destination;
    private String note;
}
