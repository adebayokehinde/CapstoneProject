package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver registerDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriverAvailability(int driverId, boolean available) {
        Driver driver = getDriverById(driverId);
        driver.setAvailable(available);
        driver.setUpdatedAt(LocalDateTime.now());
        return driverRepository.save(driver);
    }

    @Override
    public List<Driver> findAvailableDriversInRegion(String region) {
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region cannot be null or empty");
        }
        return driverRepository.findByCurrentLocationAndAvailable(region, true);
    }

    @Override
    public Driver getDriverById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Driver ID cannot be null");
        }
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + id));
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}