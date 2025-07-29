package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.TruckType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TruckDTO {
    private String truckLicensePlateNumber;
    private Double capacity;
    private TruckType truckType;
}
