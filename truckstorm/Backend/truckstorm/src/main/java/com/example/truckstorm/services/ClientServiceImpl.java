package com.example.truckstorm.services;

import com.example.truckstorm.data.models.AccountStatus;
import com.example.truckstorm.data.models.Client;
import com.example.truckstorm.data.repository.ClientRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.dtos.request.ClientLoginRequest;
import com.example.truckstorm.dtos.request.ClientRegistrationRequest;
import com.example.truckstorm.dtos.response.ClientLoginResponse;
import com.example.truckstorm.dtos.response.ClientResponse;
import com.example.truckstorm.exceptions.DuplicateClientException;
import com.example.truckstorm.exceptions.InvalidClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientServiceImpl( ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

@Override
    public ClientResponse registerClient(ClientRegistrationRequest clientRegistrationRequest) {
    validateExistence(clientRegistrationRequest);
    Client client = new Client();
    client.setFirstName(clientRegistrationRequest.getFirstName());
    client.setEmail(clientRegistrationRequest.getEmail());
    client.setPassword(bCryptPasswordEncoder.encode(clientRegistrationRequest.getPassword() ));
    client.setPhone(clientRegistrationRequest.getPhone());
    Client savedClient =  clientRepository.save(client);
    savedClient.setAccountStatus(AccountStatus.ACTIVE);
    ClientResponse clientResponse = new ClientResponse();
    clientResponse.setId(savedClient.getUserID());
    clientResponse.setMessage("Successfully registered a new client");
    clientResponse.setStatus(savedClient.getAccountStatus());
    return clientResponse;
}
    private void validateExistence(ClientRegistrationRequest clientRegistrationRequest) {
        if (clientRepository
                .findByEmail(clientRegistrationRequest
                .getEmail())!= null) throw new DuplicateClientException("Client Already Exist");
    }
    @Override
    public ClientLoginResponse login(ClientLoginRequest clientLoginRequest) {
        validateClient(clientLoginRequest);
        verifyPassword(clientLoginRequest);
        ClientLoginResponse clientLoginResponse = new ClientLoginResponse();
        clientLoginResponse.setId(clientRepository.findByEmail(clientLoginRequest
                .getEmail())
                .getUserID());
        clientLoginResponse.setMessage("logged in Successfully.");
        return clientLoginResponse;
    }

    private void verifyPassword(ClientLoginRequest clientLoginRequest) {
        if (!bCryptPasswordEncoder
                .matches(clientRepository.findByEmail(clientLoginRequest
                .getEmail())
                .getPassword(), clientLoginRequest
                .getPassword())) throw new InvalidClientException("Invalid Password");
    }

    private void validateClient(ClientLoginRequest clientLoginRequest) {
        if (clientRepository
                .findByEmail(clientLoginRequest
                .getEmail()) == null) throw new InvalidClientException("Client Does Not Exist");
    }

}
