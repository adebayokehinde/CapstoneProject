package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Truck;
import com.example.truckstorm.data.models.TruckOwner;
import com.example.truckstorm.data.models.TruckType;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.DriverLoginRequest;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import com.example.truckstorm.dtos.request.TruckDTO;
import com.example.truckstorm.dtos.response.DriverLoginResponse;
import com.example.truckstorm.dtos.response.DriverResponse;
import com.example.truckstorm.exceptions.DuplicateDriverException;
import com.example.truckstorm.exceptions.InvalidDriverException;
import com.example.truckstorm.exceptions.TruckAssignmentException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void TestThatDriverCanRegisterWithOutTruck(){
        

        driverRegistrationRequest.setOwnsTruck(false);
        DriverResponse response = driverService.registerDriver(driverRegistrationRequest);

        assertNotNull(response.getId());
        assertEquals("DriverName", response.getFirstName());
        assertThat(response.getTruckType()).isNull();

        Driver savedDriver = driverRepository.findById(response.getId()).orElseThrow();
        assertThat(savedDriver.getAssignedTruck()).isNull();


    }

    @Test
    public void duplicateDriverCanNotBeRegistered() {
        DriverRegistrationRequest sameDriverRegistration = new DriverRegistrationRequest();
        sameDriverRegistration.setFirstName("DriverName");
        sameDriverRegistration.setEmail("email@Gmail.com");
        sameDriverRegistration.setPassword("password");
        sameDriverRegistration.setDriverLicenseNumber("DriversLicense123456");
        sameDriverRegistration.setProfileImageUrl("profileImage");
        sameDriverRegistration.setOwnsTruck(true);


        TruckDTO truckDTO2 = new TruckDTO();
        truckDTO2.setTruckLicensePlateNumber("TRUCK123");
        truckDTO2.setCapacity(5000.0);
        truckDTO2.setTruckType(TruckType.FLATBED);
        driverRegistrationRequest.setTruckDetails(truckDTO2);

        DriverResponse response = driverService.registerDriver(driverRegistrationRequest);
        
        assertThrows(DuplicateDriverException.class,()-> driverService.registerDriver(sameDriverRegistration));

        assertNotNull(response.getId());
        assertEquals("DriverName", response.getFirstName());
        assertEquals(TruckType.FLATBED, response.getTruckType());

        Driver savedDriver = driverRepository.findById(response.getId()).orElseThrow();
        assertNotNull(savedDriver.getAssignedTruck());
        assertEquals("TRUCK123", savedDriver.getAssignedTruck().getTruckLicensedPlateNumber());

    }

    @Test
    public void driverCanLoginAfterRegistering() {
        DriverRegistrationRequest secondDriverRequest = new DriverRegistrationRequest();
        secondDriverRequest.setFirstName("DriverName2");
        secondDriverRequest.setEmail("Driver2@gmail.com");
        secondDriverRequest.setPassword("password2");
        secondDriverRequest.setDriverLicenseNumber("DriversLicense123452");
        secondDriverRequest.setProfileImageUrl("profileImage2");
        secondDriverRequest.setOwnsTruck(false);

        driverService.registerDriver(driverRegistrationRequest);
        driverService.registerDriver(secondDriverRequest);
        assertEquals(2, driverRepository.count());
        DriverLoginRequest loginDetails = new DriverLoginRequest();
        loginDetails.setEmail("Driver2@gmail.com");
        loginDetails.setPassword("password2");
        DriverLoginResponse loginResponse = driverService.loginDriver(loginDetails);
        assertThat(loginResponse).isNotNull();
        assertEquals(loginResponse.getUserId(),driverRepository.findByEmail("Driver2@gmail.com").getUserID());

    }
    @Test
    public void invalidDriverCanNotLogin() {
        DriverRegistrationRequest secondDriverRequest = new DriverRegistrationRequest();
        secondDriverRequest.setFirstName("DriverName2");
        secondDriverRequest.setEmail("Driver2@gmail.com");
        secondDriverRequest.setPassword("password2");
        secondDriverRequest.setDriverLicenseNumber("DriversLicense123452");
        secondDriverRequest.setProfileImageUrl("profileImage2");
        secondDriverRequest.setOwnsTruck(false);

        driverService.registerDriver(driverRegistrationRequest);
        driverService.registerDriver(secondDriverRequest);
        assertEquals(2, driverRepository.count());
        DriverLoginRequest loginDetails = new DriverLoginRequest();
        loginDetails.setEmail("invalid@gmail.com");
        loginDetails.setPassword("password2");
        assertThrows(InvalidDriverException.class,()->driverService.loginDriver(loginDetails));
        
    }


}
