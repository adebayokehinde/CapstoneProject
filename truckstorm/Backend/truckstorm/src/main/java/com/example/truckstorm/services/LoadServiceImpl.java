package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Client;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

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
        loadPostResponse.setPostResponseId(load.getId());

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
    public Load getLoadById(int id) {

        return null;
    }

    @Override
    public List<Load> getAllLoads() {
        return loadRepository.findAll();
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