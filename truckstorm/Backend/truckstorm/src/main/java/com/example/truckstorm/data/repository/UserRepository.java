package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Client;
import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer >{
    List<User> findByEmail(String email);

    @Query("SELECT d FROM Driver d")
    List<Driver> findAllDrivers();

    @Query("SELECT c FROM Client c")
    List<Client> findAllClients();
}