package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.dtos.request.DriverRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@RequiredArgsConstructor
public class DriverServiceTest {


    @BeforeEach
    public void setup() {
        DriverRegistrationRequest driverRegistrationRequest = new DriverRegistrationRequest();
        driverRegistrationRequest.setDriverLicenseNumber("");

    }
    @Test
    public void TestThatDriverCanRegister(){



    }


}
