package com.example.truckstorm.dtos.response;

import com.example.truckstorm.data.models.LoadStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class LoadUpdateResponse {
    private int id;
    private LoadStatus loadStatus;
    private LocalDateTime UpdatedAt;

}
