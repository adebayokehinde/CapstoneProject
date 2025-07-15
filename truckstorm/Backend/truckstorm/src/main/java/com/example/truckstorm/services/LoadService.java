package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;

public interface LoadService {
    public Load postLoad(Load load);
    public Load getLoadById(Long id);

}
