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
        vehicle.setPlateNumber("ABC-123");
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
        vehicleOne.setPlateNumber("ABC-123");
        Vehicle vehicleTwo = new Vehicle();
        vehicleTwo.setPlateNumber("XYZ-789");
        vehicleRepository.save(vehicleOne);
        vehicleRepository.save(vehicleTwo);
        assertEquals(2L, vehicleRepository.count());
    }

    @Test
    public void testThatWhenFindByIncorrectPlateNumber_errorIsThrown() {
        vehicleRepository.save(vehicle);
        assertThrows(IllegalArgumentException.class, () -> vehicleRepository.findById("WRONG-000"));
    }

    @Test
    public void testThatWhenIFindByPlateNumber_vehicleIsFound() {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        String plateNumber = savedVehicle.getPlateNumber();
        assertEquals(savedVehicle, vehicleRepository.findById(plateNumber));
    }

    @Test
    public void testThatWhenIDeleteVehicle_countGoesBackToZero() {
        vehicleRepository.save(vehicle);
        vehicleRepository.del("ABC-123");
        assertEquals(0L, vehicleRepository.count());
    }

    @Test
    public void testThatWhenIDeletePlateNumberThatDoesNotExist_errorIsThrown() {
        vehicleRepository.save(vehicle);
        assertThrows(IllegalArgumentException.class, () -> vehicleRepository.del("WRONG-000"));
    }

    @Test
    public void testThatWhenIDeleteOneOfTwoVehicles_theOtherRemains() {
        Vehicle vehicleTwo = new Vehicle();
        vehicleTwo.setPlateNumber("XYZ-789");
        vehicleRepository.save(vehicle);
        vehicleRepository.save(vehicleTwo);
        vehicleRepository.del("XYZ-789");
        assertEquals(1L, vehicleRepository.count());
        assertTrue(vehicleRepository.existById("ABC-123"));
    }

    @Test
    public void testThatWhenICheckExistByVehiclePlate_itReturnsTrue() {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        assertTrue(vehicleRepository.existById(savedVehicle.getPlateNumber()));
    }

    @Test
    public void testThatWhenICheckNonExistingVehiclePlate_itReturnsFalse() {
        assertFalse(vehicleRepository.existById("WRONG-000"));
    }
}