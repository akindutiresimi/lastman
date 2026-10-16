package com.lastma.data.repositories;

import com.lastma.data.models.ViolationTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViolationTicketRepositoryImplTest {
    private ViolationTicketRepositoryImpl ticketRepository;
    private ViolationTicket ticket;

    @BeforeEach
    public void setUp() {
        ticketRepository = new ViolationTicketRepositoryImpl();
        ticket = new ViolationTicket();
        ticket.setPlateNumber("ABC-123");
    }

    @Test
    public void testThatWhenISaveTicket_countAddOne() {
        assertEquals(0L, ticketRepository.count());
        ticketRepository.save(ticket);
        assertEquals(1L, ticketRepository.count());
    }

    @Test
    public void testThatWhenTwoTicketsAreSaved_countAddTwo() {
        assertEquals(0L, ticketRepository.count());
        ViolationTicket ticketOne = new ViolationTicket();
        ticketOne.setPlateNumber("ABC-123");
        ViolationTicket ticketTwo = new ViolationTicket();
        ticketTwo.setPlateNumber("XYZ-789");
        ticketRepository.save(ticketOne);
        ticketRepository.save(ticketTwo);
        assertEquals(2L, ticketRepository.count());
    }

    @Test
    public void testThatWhenIFindByPlateNumber_ticketIsFound() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        String plateNumber = savedTicket.getPlateNumber();
        assertEquals(savedTicket, ticketRepository.findById(plateNumber));
    }

    @Test
    public void testThatWhenIDeleteTicket_countGoesBackToZero() {
        ticketRepository.save(ticket);
        ticketRepository.del("ABC-123");
        assertEquals(0L, ticketRepository.count());
    }

    @Test
    public void testThatWhenIDeleteOneOfTwoTickets_theOtherRemains() {
        ViolationTicket ticketTwo = new ViolationTicket();
        ticketTwo.setPlateNumber("XYZ-789");
        ticketRepository.save(ticket);
        ticketRepository.save(ticketTwo);
        ticketRepository.del("XYZ-789");
        assertEquals(1L, ticketRepository.count());
        assertTrue(ticketRepository.existById("ABC-123"));
    }

    @Test
    public void testThatWhenICheckExistByTicketPlate_itReturnsTrue() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        assertTrue(ticketRepository.existById(savedTicket.getPlateNumber()));
    }

    @Test
    public void testThatWhenICheckNonExistingTicketPlate_itReturnsFalse() {
        assertFalse(ticketRepository.existById("WRONG-000"));
    }
}