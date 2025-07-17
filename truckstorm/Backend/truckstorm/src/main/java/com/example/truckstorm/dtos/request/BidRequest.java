package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@NoArgsConstructor
public class BidRequest {
    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer loadId;

    @NotNull
    private Integer driverId;

    private LocalDateTime proposedPickupTime;
    private String note;
}
