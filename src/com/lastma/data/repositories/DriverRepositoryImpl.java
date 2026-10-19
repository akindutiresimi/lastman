package com.lastma.data.repositories;

import com.lastma.data.models.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverRepositoryImpl implements DriverRepository{
    private static List<Driver> drivers = new ArrayList<>();
    private int numberOfDrivers = 0;

    @Override
    public long count() {
        return drivers.size();
    }

    @Override
    public Driver save(Driver driver) {
        driver.setId(++ numberOfDrivers);
        drivers.add(driver);
        return driver;
    }

    @Override
    public Driver findById(int id) {
        for(Driver driver: drivers) {
            if(driver.getId() == id){
                return driver;
            }
        }
        throw new IllegalArgumentException("Driver with Id" + id + "not found");
    }

    @Override
    public void del(int id) {
        for(Driver driver : drivers){
            if(driver.getId() == id){
                drivers.remove(driver);
                break;
            }
            else if (driver.getId() != id){
                throw new IllegalArgumentException("Driver with Id" + id + "not found");
            }
        }

    }

    @Override
    public boolean existById(int driverId) {
        for(Driver driver : drivers){
            if(driver.getId() == driverId){
                return true;
            }
        }
        return false;
    }

    @Override
    public Driver findByEmail(String email) {
        for (Driver driver : drivers){
            if(driver.getEmail().equals(email)){
                return driver;
            }
        }
        return null;
    }

    public void deleteAll() {
        drivers.clear();
        numberOfDrivers = 0;
    }
}
