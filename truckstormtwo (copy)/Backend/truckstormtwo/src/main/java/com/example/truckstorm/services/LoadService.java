package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;

import java.util.List;

public interface LoadService {
    Load postLoad(Load load);
    Load getLoadById(Long id);
    List<Load> getAllLoads();
    List<Load> getLoadsByClientId(String clientId);
    Load updateLoadStatus(Long loadId, LoadStatus status);
    void deleteLoad(Long id);


}
