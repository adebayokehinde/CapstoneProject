package com.example.truckstorm.data.repository;

import com.example.truckstorm.data.models.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<Load, Long> {

}
