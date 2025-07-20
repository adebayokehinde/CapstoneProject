package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@RequiredArgsConstructor
public class DriverServiceTest {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private LoadService loadService;
    @Autowired
    private LoadRepository loadRepository;


    @BeforeEach
    public void setup() {
        DriverRegistrationRequest driverRegistrationRequest = new DriverRegistrationRequest();
        driverRegistrationRequest.setMaxLoadCapacity(2000.0);
        driverRegistrationRequest.setDriverLicenseNumber("DriversLicenseNumber");
        driverRegistrationRequest.setTruckLicenseNumber("TruckLicenseNumber");
        driverRegistrationRequest.setPlateNumber("PlateNumber");
        driverRegistrationRequest.setImageUrl("image");
        driverRegistrationRequest.setProfileImageUrl("profileImage");
        Driver driver = new Driver();
        driver.setDriverLicenseNumber(driverRegistrationRequest.getDriverLicenseNumber());




        driverRepository.save(driver);


    }
    @AfterEach
    public void tearDown() {
        driverRepository.deleteAll();
    }
    @Test
    public void TestThatDriverCanRegister(){




    }


}
