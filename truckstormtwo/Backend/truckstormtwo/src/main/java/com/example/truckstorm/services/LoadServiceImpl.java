package com.example.truckstorm.services;

import com.example.truckstorm.data.models.Load;
import com.example.truckstorm.data.models.LoadStatus;
import com.example.truckstorm.data.repository.LoadRepository;
import com.example.truckstorm.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LoadServiceImpl implements LoadService {

    private final LoadRepository loadRepository;

    public LoadServiceImpl(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @Override
    public Load postLoad(Load load) {
        if (load == null) {
            throw new IllegalArgumentException("Load cannot be null");
        }

        // Set default status if not provided
        if (load.getLoadStatus() == null) {
            load.setLoadStatus(LoadStatus.PENDING);
        }


        load.setCreatedAt(LocalDateTime.now());
        load.setUpdatedAt(LocalDateTime.now());

        return loadRepository.save(load);
    }

    @Override
    public Load getLoadById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Load ID cannot be null");
        }

        return loadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with id: " + id));
    }

    @Override
    public List<Load> getAllLoads() {
        return loadRepository.findAll();
    }

    @Override
    public List<Load> getLoadsByClientId(String clientId) {
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty");
        }

        return loadRepository.findByClientId(clientId);
    }

    @Override
    public Load updateLoadStatus(Long loadId, LoadStatus status) {
        if (loadId == null) {
            throw new IllegalArgumentException("Load ID cannot be null");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        Load load = getLoadById(loadId);
        load.setLoadStatus(status);
        load.setUpdatedAt(LocalDateTime.now());

        return loadRepository.save(load);
    }

    @Override
    public void deleteLoad(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Load ID cannot be null");
        }

        if (!loadRepository.existsById(id)) {
            throw new ResourceNotFoundException("Load not found with id: " + id);
        }

        loadRepository.deleteById(id);
    }
}