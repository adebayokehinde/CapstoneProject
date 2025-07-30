package com.example.truckstorm.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindCompatibleDriversRequestDTO {
    @NotNull(message = "Load ID cannot be null")
    private Long loadId;
}
