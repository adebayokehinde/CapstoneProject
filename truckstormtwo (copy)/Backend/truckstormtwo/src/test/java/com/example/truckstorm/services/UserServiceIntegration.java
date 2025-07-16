package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Client;
import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.User;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceIntegration {
    @Autowired
    private UserService userService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Driver testDriver;
    private Client testClient;

    @BeforeEach
    void setUp() {
        testDriver = new Driver();
        testDriver.setUserID(1L);
        testDriver.setFirstName("Test Driver");
        driverRepository.save(testDriver);

        testClient = new Client();
        testClient.setUserID(1L);
        testClient.setFirstName("Test Client");
        clientRepository.save(testClient);
    }

    @Test
    void getDriverById_ShouldReturnDriver() {
        User result = userService.getUserById("driver1");

        assertNotNull(result);
        assertEquals("driver1", result.getUserID());
        assertEquals("Test Driver", result.getName());
        assertTrue(result instanceof Driver);
    }

    @Test
    void updateClientProfile_ShouldUpdateSuccessfully() {
        Client updatedClient = new Client();
        updatedClient.setName("Updated Client");

        User result = userService.updateUserProfile("client1", updatedClient);

        assertNotNull(result);
        assertEquals("client1", result.getUserID());
        assertEquals("Updated Client", result.getName());
    }
}
