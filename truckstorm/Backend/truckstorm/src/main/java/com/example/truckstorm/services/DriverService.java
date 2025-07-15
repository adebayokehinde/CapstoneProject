package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;

import java.util.List;

public interface DriverService {
    Driver registerDriver(Driver driver);
    Driver updateDriverAvailability(Long driverId, boolean available);
    List<Driver> findAvailableDriversInRegion(String region);
    Driver getDriverById(Long id);
    List<Driver> getAllDrivers();
}
