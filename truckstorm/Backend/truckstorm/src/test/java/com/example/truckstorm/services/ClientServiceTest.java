package com.example.truckstorm.services;

import com.example.truckstorm.data.models.LoadType;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.ClientLoginRequest;
import com.example.truckstorm.dtos.request.ClientPostRequest;
import com.example.truckstorm.dtos.request.ClientRegistrationRequest;
import com.example.truckstorm.dtos.request.LoadUploadRequest;
import com.example.truckstorm.dtos.response.ClientLoginResponse;
import com.example.truckstorm.dtos.response.ClientPostResponse;
import com.example.truckstorm.dtos.response.ClientResponse;
import com.example.truckstorm.exceptions.DuplicateClientException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Autowired
    private ClientService clientService;

    ClientRegistrationRequest registration;
    @BeforeEach
    public void setUp() {
        registration = new ClientRegistrationRequest();
        registration.setFirstName("John");
        registration.setEmail("Client@gmail.com");
        registration.setPhone("12345678910");
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
    public void ClientCanRegisterTest(){
        ClientResponse savedClient = clientService.registerClient(registration);
        assertThat(clientRepository.count()).isEqualTo(1);
        assertThat(clientRepository
                .findByEmail(registration.getEmail())
                .getUserID())
                .isEqualTo(savedClient.getId());
        assertThat(savedClient.getId()).isNotEqualTo(0);
    }
    @Test
    public void DuplicateClientCannotRegisterTest(){
        ClientResponse savedClient = clientService.registerClient(registration);
        assertThat(clientRepository.count()).isEqualTo(1);
        assertThrows( DuplicateClientException.class,()-> clientService.registerClient(registration));
        assertThat(savedClient.getId()).isNotEqualTo(0);
    }
    @Test
    public void clientCanLoginTest(){
        ClientResponse savedClient = clientService.registerClient(registration);
        assertThat(clientRepository.count()).isEqualTo(1);
        assertThat(savedClient.getId()).isNotEqualTo(0);
        ClientLoginRequest clientLogin = new ClientLoginRequest();
        clientLogin.setEmail(registration.getEmail());
        clientLogin.setPassword(registration.getPassword());
        ClientLoginResponse loginResponse = clientService.login(clientLogin);
        assertEquals(savedClient.getId(),loginResponse.getId());

    }
    @Test
    public void clientCannotLoginWithInvalidDetailsTest() {
        ClientResponse savedClient = clientService.registerClient(registration);
        assertThat(clientRepository.count()).isEqualTo(1);
        assertThat(savedClient.getId()).isNotEqualTo(0);
        ClientLoginRequest clientLogin = new ClientLoginRequest();
        clientLogin.setEmail(registration.getEmail());
        clientLogin.setPassword("WrongPassword");
        assertThrows(Exception.class, () -> clientService.login(clientLogin));

        clientLogin.setEmail("wrongEmail");
        clientLogin.setPassword(registration.getPassword());
        assertThrows(Exception.class, () -> clientService.login(clientLogin));

    }
//    @Test
//    public ClientPostResponse clientCanPostALoadTest() {

//       ClientPostRequest PostRequest = new ClientPostRequest();
//
//        PostRequest.setPickupLocation("semicolon sabo yaba lagos");
//        PostRequest.setDeliveryLocation("ozone sabo yaba lagos");
//        PostRequest.setWeight(234.0);
//        PostRequest.setLoadType(LoadType.GENERAL);
//        PostRequest.setClientId(1);
//        PostRequest.setImageUrls( List.of(" imageUrl1","imageUrl2"));
//
//        ClientPostResponse clientPostResponse = clientService.postLoad( );

//        return clientPostResponse;

//    }
}
