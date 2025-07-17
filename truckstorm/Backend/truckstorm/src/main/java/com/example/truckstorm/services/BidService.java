package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Bid;
import com.example.truckstorm.data.models.BidStatus;
import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    List<Driver> findCompatibleDriversForLoad(Load load);
    Driver assignDriverToLoad(int loadId, int driverId);
    Bid createBid(Bid bid);
    Bid getBidById(int bidId);
    List<Bid> getBidsByLoadId(int loadId);
    List<Bid> getBidsByDriverId(int driverId);
    List<Bid> getBidsByClientId(int clientId);
    Bid updateBidStatus(int bidId, BidStatus status);
    Bid acceptBid(int bidId);
    Bid rejectBid(int bidId);
    void deleteBid(int bidId);

}