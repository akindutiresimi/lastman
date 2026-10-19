package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleRepositoryImplTest {
    private VehicleRepositoryImpl vehicleRepository;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        vehicleRepository = new VehicleRepositoryImpl();
        vehicle = new Vehicle();
        vehicle.setId(1);
    }

    @Test
    public void testThatWhenISaveVehicle_countAddOne() {
        assertEquals(0L, vehicleRepository.count());
        vehicleRepository.save(vehicle);
        assertEquals(1L, vehicleRepository.count());
    }

    @Test
    public void testThatWhenTwoVehiclesAreSaved_countAddTwo() {
        assertEquals(0L, vehicleRepository.count());
        Vehicle vehicleOne = new Vehicle();
        vehicleOne.setId(1);
        Vehicle vehicleTwo = new Vehicle();
        vehicleTwo.setId(2);
        vehicleRepository.save(vehicleOne);
        vehicleRepository.save(vehicleTwo);
        assertEquals(2L, vehicleRepository.count());
    }

    @Test
    public void testThatWhenFindByIncorrectId_errorIsThrown() {
        vehicleRepository.save(vehicle);
        assertThrows(IllegalArgumentException.class, () -> vehicleRepository.findById(5));
    }

    @Test
    public void testThatWhenIFindById_vehicleIsFound() {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        int id = savedVehicle.getId();
        assertEquals(savedVehicle, vehicleRepository.findById(id));
    }

    @Test
    public void testThatWhenIDeleteVehicle_countGoesBackToZero() {
        vehicleRepository.save(vehicle);
        vehicleRepository.del(1);
        assertEquals(0L, vehicleRepository.count());
    }

    @Test
    public void testThatWhenIDeleteIdThatDoesNotExist_errorIsThrown() {
        vehicleRepository.save(vehicle);
        assertThrows(IllegalArgumentException.class, () -> vehicleRepository.del(5));
    }

    @Test
    public void testThatWhenIDeleteOneOfTwoVehicles_theOtherRemains() {
        Vehicle vehicleTwo = new Vehicle();
        vehicleTwo.setId(2);
        vehicleRepository.save(vehicle);
        vehicleRepository.save(vehicleTwo);
        vehicleRepository.del(2);
        assertEquals(1L, vehicleRepository.count());
        assertTrue(vehicleRepository.existById(1));
    }

    @Test
    public void testThatWhenICheckExistByVehiclePlate_itReturnsTrue() {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        assertTrue(vehicleRepository.existById(savedVehicle.getId()));
    }

    @Test
    public void testThatWhenICheckNonExistingVehiclePlate_itReturnsFalse() {
        assertFalse(vehicleRepository.existById(5));
    }
}