package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.DriverStatus;
import com.example.truckstorm.data.models.Truck;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.TruckRepository;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import com.example.truckstorm.dtos.response.DriverResponse;
import com.example.truckstorm.dtos.response.DriverUpdateResponse;
import com.example.truckstorm.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    public final TruckRepository truckRepository;

    public DriverServiceImpl(DriverRepository driverRepository,  TruckRepository truckRepository) {
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
    }

    @Override
    public DriverResponse registerDriver(DriverRegistrationRequest driverRequest) {
        if (driverRequest == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }

        Driver driver = new Driver();
        driver.setEmail(driverRequest.getEmail());
        driver.setPassword(driverRequest.getPassword());
        driver.setFirstName(driverRequest.getFirstName());
        driver.setDriverLicenseNumber(driverRequest.getDriverLicenseNumber());
        driver.setProfileImageUrl(driverRequest.getProfileImageUrl());
        driver.setOwnedTrucks(new ArrayList<>());
        driverRepository.save(driver);
        System.out.println("Driver registered successfully  "+ driverRepository.findByEmail(driverRequest.getEmail()).getUserID());


        if (driverRequest.isOwnsTruck()) {
            Truck truck = new Truck();
            truck.setTruckLicensedPlateNumber(driverRequest.getTruckDetails().getTruckLicensePlateNumber());
            truck.setCapacity(driverRequest.getTruckDetails().getCapacity());
            truck.setTruckType(driverRequest.getTruckDetails().getTruckType());
            truck.setOwner(driverRepository.findByEmail(driverRequest.getEmail()));
            driver.getOwnedTrucks().add(truck);
            truck.setDriver(driverRepository.findByEmail(driverRequest.getEmail()));
            truckRepository.save(truck);
            driver.setAssignedTruck(truckRepository.findByTruckLicensedPlateNumber(truck.getTruckLicensedPlateNumber()));
        }

        DriverResponse driverResponse = new DriverResponse();
        driverResponse.setDriverStatus(DriverStatus.AVAILABLE);
        driverResponse.setId(driverRepository.findByEmail(driverRequest.getEmail()).getUserID());
        driverResponse.setFirstName(driverRepository.findByEmail(driverRequest.getEmail()).getFirstName());
        driverResponse.setDriverLicenseNumber(driverRepository.findByEmail(driverRequest.getEmail()).getDriverLicenseNumber());

        if (driverRequest.isOwnsTruck()) {
            driverResponse.setTruckType(driverRequest.getTruckDetails().getTruckType());
        }

        return driverResponse;
    }



    @Override
    public DriverUpdateResponse updateDriverAvailability(int driverId, boolean available) {
        Driver driver = getDriverById(driverId);
        driver.setAvailable(available);
        driver.setUpdatedAt(LocalDateTime.now());
        driverRepository.save(driver);
        return null;
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