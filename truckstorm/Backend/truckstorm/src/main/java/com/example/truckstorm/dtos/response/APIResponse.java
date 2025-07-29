package com.example.truckstorm.dtos.response;

import lombok.*;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private boolean success;
    private Object data;
    private String Status;

}

