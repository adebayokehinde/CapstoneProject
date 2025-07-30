package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.dtos.response.LoadUpdateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadService {
    LoadPostResponse postLoad(LoadUploadRequest loadUploadRequest);
    LoadPostResponse getLoadById(int id);
    List<LoadResponse> getAllLoads();
    List<LoadResponse> getLoadsByClientId(int clientId);
    LoadUpdateResponse updateLoadStatus(int loadId, String status);
    void deleteLoad(int id);


}
