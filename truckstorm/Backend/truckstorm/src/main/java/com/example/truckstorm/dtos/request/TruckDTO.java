package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.TruckType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckDTO {
    private String truckLicensePlateNumber;
    private Double capacity;
    private TruckType truckType;
}
