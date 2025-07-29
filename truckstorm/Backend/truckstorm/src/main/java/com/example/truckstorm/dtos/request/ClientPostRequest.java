package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.LoadType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClientPostRequest {
    private String pickupLocation;
    private String deliveryLocation;
    private Double weight;
    private LoadType loadType;
    private int clientId;
    private List<String> imageUrls;
}
