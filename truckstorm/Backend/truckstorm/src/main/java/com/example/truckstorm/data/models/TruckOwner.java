package com.example.truckstorm.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@DiscriminatorValue("Truck")
public class TruckOwner extends User {
    private String companyName;

    @Column(unique = true)
    @NotBlank(message = "Operating license number is required")
    private String operatingLicenseNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Truck> ownedTrucks;

}