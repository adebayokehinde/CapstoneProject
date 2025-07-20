package com.example.truckstorm.data.models;

import lombok.Getter;

@Getter
public enum TruckType {
    DRY_VAN(20_000),
    FLATBED(40_000),
    REEFER(35_000),
    TANKER(30_000),
    CONTAINER_CARRIER(60_000),
    CAR_HAULER(25_000),
    DUMP_TRUCK(80_000),
    LOWBOY(50_000),
    BOX_TRUCK(10_000),
    PICKUP(1_500);

    private final int maxCapacityKg;

    TruckType(int maxCapacityKg) {
        this.maxCapacityKg = maxCapacityKg;
    }

    public boolean canCarry(double loadWeightKg) {
        return loadWeightKg <= maxCapacityKg;
    }
}
