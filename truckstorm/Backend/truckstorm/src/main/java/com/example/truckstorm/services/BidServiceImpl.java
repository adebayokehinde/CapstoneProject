package com.example.truckstorm.services;

import com.example.truckstorm.data.models.*;
import com.example.truckstorm.data.repository.BidRepository;
import com.example.truckstorm.data.repository.DriverRepository;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.dtos.request.BidRequest;
import com.example.truckstorm.dtos.response.*;
import com.example.truckstorm.exceptions.DriverNotAvailableException;
import com.example.truckstorm.exceptions.InvalidBidException;
import com.example.truckstorm.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
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

public BidResponse createBid(BidRequest bidRequest) {

    Load load = loadRepository.findById(bidRequest.getLoadId())
        .orElseThrow(() -> new ResourceNotFoundException("Load not found with id: " + bidRequest.getLoadId()));

    Bid bid = new Bid();
    bid.setLoad(load);
    bid.setBidStatus(BidStatus.PENDING);
    bid.setBidTimestamp(Instant.now());
    bid.setPickupLocation(load.getPickupLocation());
    bid.setDestination(load.getDeliveryLocation());
    bid.setNote(load.getNote());


    Bid savedBid = bidRepository.save(bid);


    BidResponse bidResponse = new BidResponse();
        bidResponse.setId(savedBid.getId());
        bidResponse.setPrice(savedBid.getPrice());
        bidResponse.setLoadId(savedBid.getLoad().getId()) ;
        bidResponse.setBidStatus(savedBid.getBidStatus().toString());
        bidResponse.setNote(savedBid.getNote());
        return bidResponse;
    }

    private void validatePrice(BidRequest bidRequest) {
        if (bidRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBidException("Bid price must be positive");
        }
    }


    @Override
    public List<Driver> findCompatibleDriversForLoad(Load load) {
//        List<Driver> nearbyDrivers = driverRepository.findByCurrentLocationAndAvailable(load.getPickupLocation(), true);
//
//        return nearbyDrivers.stream()
//                .filter(driver -> driver.getMaxLoadCapacity() >= load.getWeight())
//                .filter(driver -> isTruckTypeCompatible( driver.getTruckType() , load.getLoadType()))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public Driver assignDriverToLoad(int loadId, int driverId) {
//        LoadPostResponse load = loadService.getLoadById(loadId);
//        Driver driver = driverService.getDriverById(driverId);
//
//        if (!findCompatibleDriversForLoad(load).contains(driver)) {
//            throw new IllegalArgumentException("Driver is not compatible with this load");
//        }
//
//        load.setLoadStatus(LoadStatus.ASSIGNED);
//       load.setUpdatedAt(java.time.LocalDateTime.now());
//        loadRepository.save(load);
//
//        driver.setAvailable(false);
//        driver.setUpdatedAt(java.time.LocalDateTime.now());
//        return driverRepository.save(driver);
        return null;
    }

    @Override
    public BidResponse getBidById(int bidId) {
//        if (bidId == 0) {
//            throw new IllegalArgumentException("Bid ID cannot be null");
//        }
//
//        return bidRepository.findById(bidId)
//                .orElseThrow(() -> new IllegalArgumentException("Bid not found with id: " + bidId));
        return null;
    }

    @Override
    public List<Bid> getBidsByLoadId(int loadId) {
        if (loadId == 0) {
            throw new IllegalArgumentException("Load ID cannot be null");
        }
//        return bidRepository.findByLoadId(loadId);
        return null;
    }

    @Override
    public List<Bid> getBidsByDriverId(int driverId) {
        if (driverId == 0) {
            throw new IllegalArgumentException("Driver ID cannot be null");
        }
        return bidRepository.findByDriverId(driverId);
    }

    @Override
    public List<Bid> getBidsByClientId(int clientId) {
        if (clientId == 0) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
//       return bidRepository.findByClientId(clientId);
        return null;
    }


    @Override
    public BidUpdateResponse updateBidStatus(int bidId, BidStatus status) {
//        if (bidId == 0) {
//            throw new IllegalArgumentException("Bid ID cannot be null");
//        }
//        if (status == null) {
//            throw new IllegalArgumentException("Status cannot be null");
//        }
//
//        Bid bid = getBidById(bidId);
//        bid.setBidStatus(status);
        return null;
    }

    @Override
    public BidAcceptedResponse acceptBid(int bidId) {
//        Bid bid = updateBidStatus(bidId, BidStatus.ACCEPTED);
//
//        // When a bid is accepted, assign the driver to the load
//        if (bid.getLoad() != null && bid.getDriver() != null) {
//            assignDriverToLoad(bid.getLoad().getId(), bid.getDriver().getUserID());
//        }

        return null;
    }

    @Override
    public BidRejectedResponse rejectBid(int bidId) {
        return null;
    }

    @Override
    public void deleteBid(int bidId) {
//        if (bidId == 0) {
//            throw new IllegalArgumentException("Bid ID cannot be null");
//        }
//
//        Bid bid = getBidById(bidId);
//        bidRepository.delete(bid);
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
