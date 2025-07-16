package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    private final DriverRepository driverRepository;
    private final LoadRepository loadRepository;
    private final LoadService loadService;
    private final DriverService driverService;
    private final BidRepository bidRepository;


    public BidServiceImpl(DriverRepository driverRepository,
                          LoadRepository loadRepository,
                          LoadService loadService,
                          DriverService driverService,
                          BidRepository bidRepository) {
        this.driverRepository = driverRepository;
        this.loadRepository = loadRepository;
        this.loadService = loadService;
        this.driverService = driverService;
        this.bidRepository = bidRepository;
    }

    @Override
    public List<Driver> findCompatibleDriversForLoad(Load load) {
        List<Driver> nearbyDrivers = driverRepository.findByCurrentLocationAndAvailable(load.getPickupLocation(), true);

        return nearbyDrivers.stream()
                .filter(driver -> driver.getMaxLoadCapacity() >= load.getWeight())
                .filter(driver -> isTruckTypeCompatible( driver.getTruckType() , load.getLoadType()))
                .collect(Collectors.toList());
    }

    @Override
    public Driver assignDriverToLoad(Long loadId, Long driverId) {
        Load load = loadService.getLoadById(loadId);
        Driver driver = driverService.getDriverById(driverId);

        if (!findCompatibleDriversForLoad(load).contains(driver)) {
            throw new IllegalArgumentException("Driver is not compatible with this load");
        }

        load.setLoadStatus(LoadStatus.ASSIGNED);
        load.setUpdatedAt(java.time.LocalDateTime.now());
        loadRepository.save(load);

        driver.setAvailable(false);
        driver.setUpdatedAt(java.time.LocalDateTime.now());
        return driverRepository.save(driver);
    }

    @Override
    public Bid createBid(Bid bid) {
        if (bid == null) {
            throw new IllegalArgumentException("Bid cannot be null");
        }

        if (bid.getBidStatus() == null) {
            bid.setBidStatus(BidStatus.PENDING);
        }
        if (bid.getBidTimestamp() == null) {
            bid.setBidTimestamp(Instant.now());
        }

        return bidRepository.save(bid);
    }

    @Override
    public Bid getBidById(Long bidId) {
        if (bidId == null) {
            throw new IllegalArgumentException("Bid ID cannot be null");
        }

        return bidRepository.findById(bidId)
                .orElseThrow(() -> new IllegalArgumentException("Bid not found with id: " + bidId));
    }

    @Override
    public List<Bid> getBidsByLoadId(Long loadId) {
        if (loadId == null) {
            throw new IllegalArgumentException("Load ID cannot be null");
        }
        return bidRepository.findByLoadId(loadId);
    }

    @Override
    public List<Bid> getBidsByDriverId(Long driverId) {
        if (driverId == null) {
            throw new IllegalArgumentException("Driver ID cannot be null");
        }
        return bidRepository.findByDriverId(driverId);
    }

    @Override
    public List<Bid> getBidsByClientId(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        return bidRepository.findByClientId(clientId);
    }

    @Override
    public Bid updateBidStatus(Long bidId, BidStatus status) {
        if (bidId == null) {
            throw new IllegalArgumentException("Bid ID cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        Bid bid = getBidById(bidId);
        bid.setBidStatus(status);
        return bidRepository.save(bid);
    }

    @Override
    public Bid acceptBid(Long bidId) {
        Bid bid = updateBidStatus(bidId, BidStatus.ACCEPTED);

        // When a bid is accepted, assign the driver to the load
        if (bid.getLoad() != null && bid.getDriver() != null) {
            assignDriverToLoad(bid.getLoad().getId(), bid.getDriver().getUserID());
        }

        return bid;
    }

    @Override
    public Bid rejectBid(Long bidId) {
        return updateBidStatus(bidId, BidStatus.REJECTED);
    }

    @Override
    public void deleteBid(Long bidId) {
        if (bidId == null) {
            throw new IllegalArgumentException("Bid ID cannot be null");
        }

        Bid bid = getBidById(bidId);
        bidRepository.delete(bid);
    }

    private boolean isTruckTypeCompatible(TruckType truckType, LoadType loadType) {
        if (truckType == null || loadType == null) {
            return false;
        }

        if ("Refrigerated".equalsIgnoreCase(loadType.toString())) {
            return "Refrigerated".equalsIgnoreCase(truckType.toString());
        }
        if ("Hazardous".equalsIgnoreCase(loadType.toString())) {
            return "Tanker".equalsIgnoreCase(truckType.toString()) || "Flatbed".equalsIgnoreCase(truckType.toString());
        }
        return true;
    }
}
