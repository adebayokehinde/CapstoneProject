package com.example.truckstorm.dtos.response;

import com.example.truckstorm.data.models.LoadStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoadUpdateResponse {
    private int id;
    private LoadStatus loadStatus;
    private LocalDateTime UpdatedAt;

}
