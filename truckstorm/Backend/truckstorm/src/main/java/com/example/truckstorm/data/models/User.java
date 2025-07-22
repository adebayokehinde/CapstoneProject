package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Setter
@Getter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @NotBlank(message = "Name is required")
    private String firstName;
    private String lastName;

    private String phone;

    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @Column(nullable = false)

    @NotBlank(message = "Password is required")
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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private BigDecimal walletBalance;


}
