package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Bid;
import com.example.truckstorm.data.models.BidStatus;
import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.dtos.request.BidRequest;
import com.example.truckstorm.dtos.response.BidAcceptedResponse;
import com.example.truckstorm.dtos.response.BidResponse;
import com.example.truckstorm.dtos.response.BidUpdateResponse;
import com.example.truckstorm.dtos.response.BidRejectedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    BidResponse createBid(BidRequest bidRequest);
    List<Driver> findCompatibleDriversForLoad(Load load);
    Driver assignDriverToLoad(int loadId, int driverId);
    BidResponse getBidById(int bidId);
    List<Bid> getBidsByLoadId(int loadId);
    List<Bid> getBidsByDriverId(int driverId);
    List<Bid> getBidsByClientId(int clientId);
    BidUpdateResponse updateBidStatus(int bidId, BidStatus status);
    BidAcceptedResponse acceptBid(int bidId);
    BidRejectedResponse rejectBid(int bidId);
    void deleteBid(int bidId);

}