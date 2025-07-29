package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.BidStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BidUpdateRequest {
    private BigDecimal price;
    private BidStatus bidStatus;
    private LocalDateTime proposedPickupTime;
    private String note;
}
