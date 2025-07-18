package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.TruckType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DriverUpdate {
    private TruckType truckType;
    private Double maxLoadCapacity;
    private Boolean available;
    private String currentLocation;
}
