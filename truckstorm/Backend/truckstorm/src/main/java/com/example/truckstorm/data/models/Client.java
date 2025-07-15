package com.example.truckstorm.data.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@Setter
@Getter
public class Client extends User {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> loads;
    private AccountStatus accountStatus;



}
