package com.lastma.services;

import com.lastma.data.models.Driver;
import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;


public class DriverServiceImpl implements DriverService {
    private DriverRepository driverRepository = new DriverRepositoryImpl();

    @Override
    public void register(String fullName, String email, String password) {
        if(driverRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("The Email exist, choose another email");
        }
        Driver newDriver = new Driver();
        newDriver.setFullName(fullName);
        newDriver.setEmail(email);
        newDriver.setPassword(password);
        driverRepository.save(newDriver);
    }

}
