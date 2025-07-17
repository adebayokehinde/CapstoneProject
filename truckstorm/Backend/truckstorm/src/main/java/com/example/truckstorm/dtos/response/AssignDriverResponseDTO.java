package com.example.truckstorm.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssignDriverResponseDTO {
    private Long assignmentId;
//    private DriverDTO driver;
//    private LoadDTO load;
    private String status;
    private LocalDateTime assignmentTime;
}
