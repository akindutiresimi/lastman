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
    public Vehicle findById(int id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return vehicle;
            }
        }
        throw new IllegalArgumentException("Vehicle with plate number " + id + " not found");
    }

    @Override
    public void del(int id) {
        Vehicle vehicle = findById(id);
        vehicles.remove(vehicle);
    }

    @Override
    public boolean existById(int id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }
}