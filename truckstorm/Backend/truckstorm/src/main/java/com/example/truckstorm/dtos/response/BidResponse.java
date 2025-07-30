package com.example.truckstorm.dtos.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BidResponse {
    private Integer id;
    private String token;
    private BigDecimal price;
    private String bidStatus;
    private Integer loadId;
    private Integer driverId;
    private Instant bidTimestamp;
    private String note;

}
