package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@Getter
@Setter
public class Client extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_loads",
            joinColumns = @JoinColumn(name = "client_id")
    )
    @Column(name = "load_id")
    private List<String> loads;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
