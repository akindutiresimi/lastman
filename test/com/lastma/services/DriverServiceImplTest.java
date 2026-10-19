package com.lastma.services;

import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceImplTest {
    private DriverService driverService;
    private DriverRepository driverRepository = new DriverRepositoryImpl();

    @BeforeEach
    public void setUp() {

        driverService = new DriverServiceImpl();
    }

    @Test
    public void testThatICanRegisterAPoliceAnTheCountWillBeOne() {
        assertEquals(0L, driverRepository.count());
        driverService.register("full name", "email", "password");
        assertEquals(1L, driverRepository.count());

    }

    @Test
    public void testThatAnExceptionIsThrowWhenYouRegisterTheSameUserAgain() {
        assertEquals(0L, driverRepository.count());
        driverService.register("full name", "samuel@mail", "password");
        assertEquals(1L, driverRepository.count());
        assertThrows(IllegalArgumentException.class, () -> driverService.register("full name", "samuel@mail", "password"));
        assertEquals(1L, driverRepository.count());
    }
}
