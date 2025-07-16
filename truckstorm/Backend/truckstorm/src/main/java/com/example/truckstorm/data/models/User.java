package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Setter
@Getter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @NotBlank(message = "Name is required")
    private String firstName;
    private String lastName;
    private String phone;
    @NotBlank(message = "Name is required")
    private String email;
    @NotBlank(message = "Name is required")
    private String password;
    private String address;

    @NotBlank(message = "Current location is required")
    private String currentLocation;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> accountNumbers;
    @Enumerated(EnumType.STRING)
    private UserType usertype;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private BigDecimal walletBalance;


}
