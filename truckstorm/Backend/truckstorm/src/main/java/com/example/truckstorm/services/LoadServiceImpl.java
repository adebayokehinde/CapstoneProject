package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.exceptions.LoadNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class LoadServiceImpl implements LoadService {

    private Logger logger = LoggerFactory.getLogger(LoadServiceImpl.class);

    private final LoadRepository loadRepository;
    private final ClientRepository clientRepository;

    public LoadServiceImpl(LoadRepository loadRepository, ClientRepository clientRepository) {
        this.loadRepository = loadRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public LoadPostResponse postLoad(LoadUploadRequest loadUploadRequest) {
        LoadPostResponse loadPostResponse = new LoadPostResponse();


//        Client client = ValidateClientExists(loadUploadRequest);
        Load load = new Load();
        load.setPickupLocation(loadUploadRequest.getPickupLocation());
        load.setDeliveryLocation(loadUploadRequest.getDeliveryLocation());
        load.setWeight(loadUploadRequest.getWeight());
        load.setLoadType(loadUploadRequest.getLoadType());
        load.setClientId(loadUploadRequest.getClientId());
        load.setCreatedAt(LocalDateTime.now());
        load.setUpdatedAt(LocalDateTime.now());
        load.setLoadStatus(LoadStatus.PENDING);
        loadRepository.save(load);
        loadPostResponse.setLoadUpdated(true);
        loadPostResponse.setPostResponseId(load.getClientId());
        loadPostResponse.setLoadId(load.getId());
        loadPostResponse.setLoadStatus(LoadStatus.PENDING);

        logger.debug("Post Load Response: {}", loadPostResponse);

        if (load.getId() != 0){
            loadPostResponse.setLoadStatus(load.getLoadStatus());
        }

        return loadPostResponse;

    }

//    private Client ValidateClientExists(LoadUploadRequest loadUploadRequest) {
//       return clientRepository.findById(loadUploadRequest.getClientId())
//                .orElseThrow(new Supplier<RuntimeException>() {
//                    @Override
//                    public RuntimeException get() {
//                        return new RuntimeException("Client not found with id: " + loadUploadRequest .getClientId());
//                    }
//                });
//    }

    @Override
    public LoadPostResponse getLoadById(int id) {
        Load load = loadRepository.findById(id)
                .orElseThrow(() -> new LoadNotFoundException("Load not found with id: " + id));
        LoadPostResponse loadPostResponse = new LoadPostResponse();
        loadPostResponse.setLoadUpdated(true);
        loadPostResponse.setPostResponseId(load.getId());
        loadPostResponse.setLoadStatus(load.getLoadStatus());
        loadPostResponse.setClientId(load.getClientId());
        loadPostResponse.setCreatedAt(load.getCreatedAt());
        loadPostResponse.setLoadType(load.getLoadType());
        loadPostResponse.setPickupLocation(load.getPickupLocation());
        loadPostResponse.setDeliveryLocation(load.getDeliveryLocation());
        loadPostResponse.setWeight(load.getWeight());

        return loadPostResponse;

    }

    @Override
    public List<LoadResponse> getAllLoads() {
       List <Load> loads = loadRepository.findAll();
       List<LoadResponse> loadResponses  = new ArrayList<>();
       for (Load load : loads) {
           LoadResponse loadResponse = new LoadResponse();
           loadResponse.setLoadUpdated(true);
           loadResponse.setPostResponseId(load.getId());
           loadResponse.setLoadStatus(load.getLoadStatus());
           loadResponse.setClientId(load.getClientId());
           loadResponse.setCreatedAt(load.getCreatedAt());
           loadResponse.setLoadType(load.getLoadType());
           loadResponse.setPickupLocation(load.getPickupLocation());
           loadResponse.setDeliveryLocation(load.getDeliveryLocation());
           loadResponse.setWeight(load.getWeight());
           loadResponses.add(loadResponse);
       }

        return loadResponses;

    }

    @Override
    public List<Load> getLoadsByClientId(int clientId) {

        return null;
    }

    @Override
    public Load updateLoadStatus(int loadId, LoadStatus status) {

        return null;
    }

    @Override
    public void deleteLoad(int id) {

    }
}