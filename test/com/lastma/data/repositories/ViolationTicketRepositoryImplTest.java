package com.lastma.data.repositories;

import com.lastma.data.models.ViolationTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ViolationTicketRepositoryImplTest {
    private ViolationTicketRepositoryImpl ticketRepository;
    private ViolationTicket ticket;

    @BeforeEach
    public void setUp() {
        ticketRepository = new ViolationTicketRepositoryImpl();
        ticket = createTicket("ABC-123-XY");
    }

    private ViolationTicket createTicket(String plateNumber) {
        ViolationTicket newTicket = new ViolationTicket();
        newTicket.setPlateNumber(plateNumber);
        newTicket.setOffence("Wrong parking");
        newTicket.setFineAmount(20000);
        newTicket.setDateIssue(new Date());
        return newTicket;
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
        ticketRepository.save(createTicket("ABC-123-XY"));
        ticketRepository.save(createTicket("LSR-456-AB"));
        assertEquals(2L, ticketRepository.count());
    }

    @Test
    public void testThatSavedTicketKeepsItsPlateNumber() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        assertEquals("ABC-123-XY", savedTicket.getPlateNumber());
    }

    @Test
    public void testThatWhenISaveTicketWithSamePlateNumber_countStaysOne() {
        ticketRepository.save(createTicket("ABC-123-XY"));
        ticketRepository.save(createTicket("ABC-123-XY"));
        assertEquals(1L, ticketRepository.count());
    }

    @Test
    public void testThatWhenIFindByPlateNumber_ticketIsFound() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        String plateNumber = savedTicket.getPlateNumber();
        assertEquals(savedTicket, ticketRepository.findById(plateNumber));
    }

    @Test
    public void testThatWhenIDeleteTicket_countGoesBackToZero() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        ticketRepository.del(savedTicket.getPlateNumber());
        assertEquals(0L, ticketRepository.count());
    }

    @Test
    public void testThatWhenIDeleteOneOfTwoTickets_theOtherRemains() {
        ViolationTicket ticketOne = ticketRepository.save(createTicket("ABC-123-XY"));
        ViolationTicket ticketTwo = ticketRepository.save(createTicket("LSR-456-AB"));
        ticketRepository.del(ticketTwo.getPlateNumber());
        assertEquals(1L, ticketRepository.count());
        assertTrue(ticketRepository.existById(ticketOne.getPlateNumber()));
    }

    @Test
    public void testThatWhenICheckExistingPlateNumber_itReturnsTrue() {
        ViolationTicket savedTicket = ticketRepository.save(ticket);
        assertTrue(ticketRepository.existById(savedTicket.getPlateNumber()));
    }

    @Test
    public void testThatWhenICheckPlateNumberThatDoesNotExist_itReturnsFalse() {
        ticketRepository.save(ticket);
        assertFalse(ticketRepository.existById("WRONG-000"));
    }
}
