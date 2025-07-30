package com.example.truckstorm.dtos.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignDriverResponseDTO {
    private Long assignmentId;
//    private DriverDTO driver;
//    private LoadDTO load;
    private String status;
    private LocalDateTime assignmentTime;
}
