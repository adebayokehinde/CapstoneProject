package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;

import java.util.List;

public interface BidService {
    List<Driver> findCompatibleDriversForLoad(Load load);
    Driver assignDriverToLoad(Long loadId, Long driverId);
}
