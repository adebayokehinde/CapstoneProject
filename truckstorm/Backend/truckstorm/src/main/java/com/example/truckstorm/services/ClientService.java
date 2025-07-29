package com.example.truckstorm.services;

import com.example.truckstorm.dtos.request.ClientLoginRequest;
import com.example.truckstorm.dtos.request.ClientPostRequest;
import com.example.truckstorm.dtos.request.ClientRegistrationRequest;
import com.example.truckstorm.dtos.response.ClientLoginResponse;
import com.example.truckstorm.dtos.response.ClientPostResponse;
import com.example.truckstorm.dtos.response.ClientResponse;

public interface ClientService {
    ClientResponse registerClient(ClientRegistrationRequest registration);
    ClientLoginResponse login(ClientLoginRequest clientLogin);
    ClientPostResponse postLoad(ClientPostRequest clientPostRequest);
}
