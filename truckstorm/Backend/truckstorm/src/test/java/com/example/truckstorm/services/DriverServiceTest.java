package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.TruckType;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import com.example.truckstorm.dtos.request.TruckDTO;
import com.example.truckstorm.dtos.response.DriverResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    DriverRegistrationRequest driverRegistrationRequest;


    @BeforeEach
    public void setup() {
        driverRegistrationRequest = new DriverRegistrationRequest();
        driverRegistrationRequest.setFirstName("DriverName");
        driverRegistrationRequest.setEmail("email@Gmail.com");
        driverRegistrationRequest.setPassword("password");
        driverRegistrationRequest.setDriverLicenseNumber("DriversLicense123456");
        driverRegistrationRequest.setProfileImageUrl("profileImage");
        driverRegistrationRequest.setOwnsTruck(true);


        TruckDTO truckDTO = new TruckDTO();
        truckDTO.setTruckLicensePlateNumber("TRUCK123");
        truckDTO.setCapacity(5000.0);
        truckDTO.setTruckType(TruckType.FLATBED);
        driverRegistrationRequest.setTruckDetails(truckDTO);


    }
    @AfterEach
    public void tearDown() {
        driverRepository.deleteAll();
    }
    @Test
    public void TestThatDriverCanRegisterWithTruck(){



        DriverResponse response = driverService.registerDriver(driverRegistrationRequest);

        assertNotNull(response.getId());
        assertEquals("DriverName", response.getFirstName());
        assertEquals(TruckType.FLATBED, response.getTruckType());

        Driver savedDriver = driverRepository.findById(response.getId()).orElseThrow();
        assertNotNull(savedDriver.getAssignedTruck());
        assertEquals("TRUCK123", savedDriver.getAssignedTruck().getTruckLicensedPlateNumber());



    }


}
