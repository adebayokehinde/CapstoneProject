package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.AccountStatus;
import com.example.truckstorm.data.models.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository {
    List<Client> findByAccountStatus(AccountStatus status);
}
