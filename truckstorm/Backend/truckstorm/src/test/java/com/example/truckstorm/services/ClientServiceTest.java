package com.example.truckstorm.services;

import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.ClientRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class ClientServiceTest {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoadRepository loadRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private DriverRepository driverRepository;

    @BeforeEach
    public void setUp() {
        ClientRegistrationRequest registration = new ClientRegistrationRequest();
        registration.setPassword("password");
        registration.setEmail("Client@gmail.com");
        registration.setPassword("password");

    }
    @AfterEach
    public void tearDown() {
        bidRepository.deleteAll();
        loadRepository.deleteAll();
        clientRepository.deleteAll();
        driverRepository.deleteAll();
    }
    @Test
    void ClientCanRegisterTest(){


    }

}
