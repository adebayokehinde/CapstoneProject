package com.example.truckstorm.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@AllArgsConstructor
public class APIResponse {
    private boolean success;
    private Object data;
    private String Status;

}

