package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadService {
    LoadPostResponse postLoad(LoadUploadRequest loadUploadRequest);
    Load getLoadById(int id);
    List<Load> getAllLoads();
    List<Load> getLoadsByClientId(int clientId);
    Load updateLoadStatus(int loadId, LoadStatus status);
    void deleteLoad(int id);


}
