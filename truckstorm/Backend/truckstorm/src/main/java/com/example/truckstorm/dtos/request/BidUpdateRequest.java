package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.BidStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BidUpdateRequest {
    private BigDecimal price;
    private BidStatus bidStatus;
    private LocalDateTime proposedPickupTime;
    private String note;
}
