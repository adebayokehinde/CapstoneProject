package com.example.truckstorm.dtos.request;

import com.example.truckstorm.data.models.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDetails {
    private String id;
    private String email;
    private String Firstname;
    private UserType userType;
}
