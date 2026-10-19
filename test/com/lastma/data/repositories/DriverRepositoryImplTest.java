package com.lastma.data.repositories;

import com.lastma.data.models.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverRepositoryImplTest {
    private DriverRepositoryImpl driverRepository;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        driverRepository = new DriverRepositoryImpl();
        driver = new Driver();
        driverRepository.deleteAll();
    }

    @Test
    public void testThatWhenISaveDriverCount() {
        assertEquals(0L, driverRepository.count());
        driverRepository.save(driver);
        assertEquals(1L, driverRepository.count());

    }

    @Test
    public void testThatWhenTwoDriverAreSaved_countIsTwo() {
        assertEquals(0L, driverRepository.count());
        Driver driverOne = new Driver();
        Driver driverTwo = new Driver();
        driverRepository.save(driverOne);
        driverRepository.save(driverTwo);
        assertEquals(2L, driverRepository.count());
    }

    @Test
    public void testTheWhenFindByIncorrectIdErrorIsThrown() {
        driver.setId(1);
        driverRepository.save(driver);
        assertThrows(IllegalArgumentException.class, () -> driverRepository.findById(6));

    }

    @Test
    public void testThatWhenISetByIdICanFindTheDriver() {
        driver.setId(1);
        driverRepository.save(driver);
        assertNotNull(driver.getId());
    }

    @Test
    public void testThatWhenIDeletionAndCountZero() {
        driver.setId(1);
        driverRepository.save(driver);
        driverRepository.del(1);
        assertEquals(0L, driverRepository.count());
    }

    @Test
    public void testThatWhenITryDeletionOfIdThatDontExistThrowNullException() {
        driver.setId(1);
        driverRepository.save(driver);
        assertThrows(IllegalArgumentException.class, ()-> driverRepository.del(4));
    }

    @Test
    public void testWhenIFindByIdDriverIsFind() {
        Driver savedDriver = driverRepository.save(driver);
        int driverId = savedDriver.getId();
        assertEquals(savedDriver, driverRepository.findById(driverId));
    }

    @Test
    public void testThatWhenICheckExistByIdItReturnBoolean() {
        Driver savedDriver = driverRepository.save(driver);
        int driverId = savedDriver.getId();
        assertTrue(driverRepository.existById(driverId));
    }
}