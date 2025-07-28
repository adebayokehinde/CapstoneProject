package com.example.truckstorm.controllers;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.dtos.request.ClientLoginRequest;
import com.example.truckstorm.dtos.request.ClientRegistrationRequest;
import com.example.truckstorm.dtos.response.APIResponse;
import com.example.truckstorm.dtos.response.ClientLoginResponse;
import com.example.truckstorm.dtos.response.ClientResponse;
import com.example.truckstorm.dtos.response.LoadResponse;
import com.example.truckstorm.services.ClientService;
import com.example.truckstorm.services.LoadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final LoadService loadService;
    private final ClientService clientService;

    public ClientController(LoadService loadService, ClientService clientService) {
        this.loadService = loadService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> registerClient(@Valid @RequestBody ClientRegistrationRequest clientRegistrationRequestRequest) {
        try{
            ClientResponse clientResponse = clientService.registerClient(clientRegistrationRequestRequest);
            return new ResponseEntity<>(new APIResponse(true , clientResponse, "201"), HttpStatus.CREATED);
        }catch (Exception e){
            return new  ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }



//
//    @GetMapping("/{clientId}/loads")
//    public ResponseEntity<?> getClientsLoads(@PathVariable int clientId) {
//        try {
//            List<LoadResponse> loads = loadService.getLoadsByClientId(clientId);
//            return ResponseEntity.ok(loads);
//        }catch (Exception e){
//            return null;
//        }
//    }



}
