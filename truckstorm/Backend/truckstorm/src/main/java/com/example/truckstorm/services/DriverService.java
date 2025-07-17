package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {
    Driver registerDriver(Driver driver);
    Driver updateDriverAvailability(int driverId, boolean available);
    List<Driver> findAvailableDriversInRegion(String region);
    Driver getDriverById(int id);
    List<Driver> getAllDrivers();
}
