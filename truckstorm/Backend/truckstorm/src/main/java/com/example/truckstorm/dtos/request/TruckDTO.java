package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.TruckType;
import com.example.truckstorm.data.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckDTO {
    private String truckLicensePlateNumber;
    private Double capacity;
    private TruckType truckType;
}
