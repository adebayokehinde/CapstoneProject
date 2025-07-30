package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.TruckRepository;
import com.example.truckstorm.dtos.request.DriverLoginRequest;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import com.example.truckstorm.dtos.response.DriverLoginResponse;
import com.example.truckstorm.dtos.response.DriverResponse;
import com.example.truckstorm.dtos.response.DriverUpdateResponse;
import com.example.truckstorm.exceptions.DuplicateDriverException;
import com.example.truckstorm.exceptions.InvalidDriverException;
import com.example.truckstorm.exceptions.InvalidPasswordException;
import com.example.truckstorm.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public final BidRepository bidRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    public DriverServiceImpl(DriverRepository driverRepository,  TruckRepository truckRepository, BidRepository bidRepository,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.driverRepository = driverRepository;
        this.truckRepository = truckRepository;
        this.bidRepository = bidRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public DriverResponse registerDriver(DriverRegistrationRequest driverRequest) {
        if (driverRequest == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        validateExistence(driverRequest);

        Driver driver = new Driver();
        driver.setEmail(driverRequest.getEmail());
        driver.setPassword(bCryptPasswordEncoder.encode(driverRequest.getPassword()));
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

            System.out.print("TO CHECK THE LICENCE CONTENT 2 OF THE DTO" + driverRequest.getTruckDetails().getTruckLicensePlateNumber());
            System.out.printf("\n TO CHECK THE EMAIL for persistence CONTENT 2 OF THE DTO (%d)", driverRepository.findByEmail(driverRequest.getEmail()).getUserID());

            truck.setOwner(driverRepository.findByEmail(driverRequest.getEmail()));
            truck.setDriver(driverRepository.findByEmail(driverRequest.getEmail()));

            truckRepository.save(truck);
            driverRepository.findByEmail(driverRequest.getEmail()).getOwnedTrucks()
                    .add(truckRepository.findByTruckLicensedPlateNumber(truck.getTruckLicensedPlateNumber()));
            driverRepository.findByEmail(driverRequest.getEmail())
                    .setAssignedTruck(truckRepository.findByTruckLicensedPlateNumber(truck.getTruckLicensedPlateNumber()));
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

    private void validateExistence(DriverRegistrationRequest driverRequest) {
        if (driverRepository.findByEmail(driverRequest.getEmail()) != null)throw new DuplicateDriverException("Driver already exists");
    }


    @Override
    public DriverLoginResponse loginDriver(DriverLoginRequest driverLoginRequest){

        validateDriverExistence(driverLoginRequest);
        int userId = validatePassword(driverLoginRequest);

        DriverLoginResponse driverLoginResponse = new DriverLoginResponse();
        driverLoginResponse.setMessage("Login successful");
        driverLoginResponse.setUserId(userId);
        return driverLoginResponse;
    }

    private void validateDriverExistence(DriverLoginRequest driverLoginRequest) {
        if (driverRepository.findByEmail(driverLoginRequest.getEmail()) == null) throw new InvalidDriverException("Driver DoseNot Exist");
    }
    private int validatePassword(DriverLoginRequest driverLoginRequest) {
        if (bCryptPasswordEncoder.matches(driverLoginRequest.getPassword(), driverRepository.findByEmail(driverLoginRequest.getEmail()).getPassword())) return driverRepository.findByEmail(driverLoginRequest.getEmail()).getUserID();
        throw new InvalidPasswordException("Invalid Password");
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
    @Override
    public List<Bid> viewAllBids(){
        //will add geographical conditions/range
        return bidRepository.findAllByBidStatus(BidStatus.PENDING);
    }
}