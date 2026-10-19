package com.lastma.services;

import com.lastma.data.repositories.PoliceRepository;
import com.lastma.data.repositories.PoliceRepositoryImpl;
import com.lastma.data.repositories.ViolationTicketRepository;
import com.lastma.data.repositories.ViolationTicketRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoliceServiceImplTest {
    private PoliceService policeService;
    private PoliceRepository policeRepository = new PoliceRepositoryImpl();
    private ViolationTicketRepository violationTicketRepository;

    @BeforeEach
    public void setUp() {

        policeService = new PoliceServiceImpl();

    }

    @Test
    public void testThatICanRegisterAPoliceAnTheCountWillBeOne() {
        assertEquals(0L, policeRepository.count());
        policeService.register("full name", "samuel@mail", "password");
        assertEquals(1L, policeRepository.count());

    }
    @Test
    public void testThatAnExceptionIsThrowWhenYouRegisterTheSameUserAgain() {
        assertEquals(0L, policeRepository.count());
        policeService.register("full name", "samuel@mail", "password");
        assertEquals(1L, policeRepository.count());
        assertThrows(IllegalArgumentException.class, () -> policeService.register("full name", "samuel@mail", "password"));
        assertEquals(1L, policeRepository.count());
    }

//    @Test
//    public void testThatThePoliceCanFIndTicket() {
//        assertEquals(0L, violationTicketRepository.count());
//        policeService.getId()(1);
//        assertEquals(1L, violationTicketRepository.count());
//    }
}