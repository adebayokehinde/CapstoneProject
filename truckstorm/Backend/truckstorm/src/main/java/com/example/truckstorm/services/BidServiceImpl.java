package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Driver;
import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class BidServiceImpl {

    @Service
    @Transactional
    public class BidServiceImpl implements BidService {
        DriverRepository driverRepository;
        LoadRepository loadRepository;
        LoadService loadService;
        DriverService driverService;

        @Override
        public List<Driver> findCompatibleDriversForLoad(Load load) {
            List<Driver> nearbyDrivers = driverRepository.findByCurrentLocationAndAvailable(load.getPickupLocation(), true);

            return nearbyDrivers.stream()
                    .filter(driver -> driver.getMaxLoadCapacity() >= load.getWeight())
                    .filter(driver -> isTruckTypeCompatible(driver.getTruckType(), load.getLoadType()))
                    .collect(Collectors.toList());
        }

        @Override
        public Driver assignDriverToLoad(Long loadId, Long driverId) {
            Load load = loadService.getLoadById(loadId);
            Driver driver = driverService.getDriverById(driverId);

            if (!findCompatibleDriversForLoad(load).contains(driver)) {
                throw new IllegalArgumentException("Driver is not compatible with this load");
            }


            load.setStatus(LoadStatus.ASSIGNED);
            load.setUpdatedAt(java.time.LocalDateTime.now());
            loadRepository.save(load);

            driver.setAvailable(false);
            driver.setUpdatedAt(java.time.LocalDateTime.now());
            return driverRepository.save(driver);
        }

        private boolean isTruckTypeCompatible(String truckType, String loadType) {

            if ("Refrigerated".equalsIgnoreCase(loadType)) {
                return "Refrigerated".equalsIgnoreCase(truckType);
            }
            if ("Hazardous".equalsIgnoreCase(loadType)) {
                return "Tanker".equalsIgnoreCase(truckType) || "Flatbed".equalsIgnoreCase(truckType);
            }
            return true;
        }
    }
}
