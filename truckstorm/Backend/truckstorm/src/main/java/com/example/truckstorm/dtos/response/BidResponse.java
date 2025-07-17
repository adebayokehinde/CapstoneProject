package com.example.truckstorm.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class BidResponse {
    private Integer id;
    private BigDecimal price;
    private String bidStatus;
    private Integer loadId;
    private Integer driverId;
    private LocalDateTime proposedPickupTime;
    private Instant bidTimestamp;
    private String note;

}
