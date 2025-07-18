package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.dtos.request.DriverRequest;
import com.example.truckstorm.dtos.response.DriverResponse;
import com.example.truckstorm.dtos.response.DriverUpdateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DriverService {
    DriverResponse registerDriver(DriverRequest driverRequest);
    DriverUpdateResponse updateDriverAvailability(int driverId, boolean available);
    List<Driver> findAvailableDriversInRegion(String region);
    Driver getDriverById(int id);
    List<Driver> getAllDrivers();
}
