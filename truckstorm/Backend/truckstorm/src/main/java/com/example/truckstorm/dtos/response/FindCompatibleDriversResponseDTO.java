package com.example.truckstorm.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindCompatibleDriversResponseDTO {
    private Long loadId;
    private List<DriverDTO> compatibleDrivers;

}
