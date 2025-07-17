package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadService {
    LoadPostResponse postLoad(LoadUploadRequest loadUploadRequest);
    LoadPostResponse getLoadById(int id);
    List<LoadResponse> getAllLoads();
    List<Load> getLoadsByClientId(int clientId);
    Load updateLoadStatus(int loadId, LoadStatus status);
    void deleteLoad(int id);


}
