package com.example.truckstorm.services;


import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService{

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver registerDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriverAvailability(Long driverId, boolean available) {
        Driver driver = getDriverById(driverId);
        driver.setAvailable(available);
//        driver.setUpdatedAt(LocalDateTime.now());
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> findAvailableDriversInRegion(String region) {
        return driverRepository.findByCurrentLocationAndAvailable(region, true);
    }

    @Override
    public Driver getDriverById(Long id) {
//        return driverRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));
        return null;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

}
