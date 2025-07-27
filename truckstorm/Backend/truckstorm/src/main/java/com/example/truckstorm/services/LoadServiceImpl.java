package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Bid;
import com.example.truckstorm.data.models.BidStatus;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.BidRequest;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.BidResponse;
import com.example.truckstorm.dtos.response.LoadPostResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.dtos.response.LoadUpdateResponse;
import com.example.truckstorm.exceptions.InvalidLoadStatusException;
import com.example.truckstorm.exceptions.LoadNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LoadServiceImpl implements LoadService {


    private final LoadRepository loadRepository;
    private final ClientRepository clientRepository;
    private final BidRepository bidRepository;

    public LoadServiceImpl(LoadRepository loadRepository, ClientRepository clientRepository,BidRepository bidRepository) {
        this.loadRepository = loadRepository;
        this.clientRepository = clientRepository;
        this.bidRepository = bidRepository;
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
        Load savedLoad = loadRepository.save(load);
        loadPostResponse.setLoadUpdated(true);
        loadPostResponse.setPostResponseId(load.getClientId());
        loadPostResponse.setLoadStatus(LoadStatus.PENDING);
        loadPostResponse.setLoadId(savedLoad.getId());

        Bid bid = new Bid();
        bid.setLoad(savedLoad);
//        bid.setClient(ClientRepository.findByClientId(loadUploadRequest.getClientId()));
        bid.setWeight(savedLoad.getWeight());
        bid.setBidStatus(BidStatus.PENDING);
        bid.setBidTimestamp(Instant.now());
        bid.setPickupLocation(load.getPickupLocation());
        bid.setDestination(load.getDeliveryLocation());
        bid.setNote(load.getNote());
        bidRepository.save(bid);

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
    public List<LoadResponse> getLoadsByClientId(int clientId) {
     return null;
    }

    @Override
    public LoadUpdateResponse updateLoadStatus(int loadId, String status) {
        LoadStatus newStatus ;
        try {
            newStatus = LoadStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidLoadStatusException("Invalid load status: " + status);
        }

        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new LoadNotFoundException("Load not found with ID: " + loadId));

        load.setLoadStatus(newStatus);
        load.setUpdatedAt(LocalDateTime.now());

        Load updatedLoad = loadRepository.saveAndFlush(load);

        LoadUpdateResponse loadResponse = new LoadUpdateResponse();
        loadResponse.setId(updatedLoad.getId());
        loadResponse.setUpdatedAt(updatedLoad.getUpdatedAt());
        loadResponse.setLoadStatus(updatedLoad.getLoadStatus());

        return loadResponse;
    }

    @Override
    public void deleteLoad(int id) {
//        to be implemented later
    }
}