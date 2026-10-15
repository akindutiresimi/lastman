package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleRepositoryImpl implements VehicleRepository {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public long count() {
        return vehicles.size();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicles.add(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle findById(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getPlateNumber(), plateNumber)) {
                return vehicle;
            }
        }
        throw new IllegalArgumentException("Vehicle with plate number " + plateNumber + " not found");
    }

    @Override
    public void del(String plateNumber) {
        Vehicle vehicle = findById(plateNumber);
        vehicles.remove(vehicle);
    }

    @Override
    public boolean existById(String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getPlateNumber(), plateNumber)) {
                return true;
            }
        }
        return false;
    }
}