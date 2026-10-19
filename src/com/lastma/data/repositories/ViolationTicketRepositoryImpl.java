package com.lastma.data.repositories;

import com.lastma.data.models.ViolationTicket;

import java.util.ArrayList;
import java.util.List;

public class ViolationTicketRepositoryImpl implements ViolationTicketRepository {
    private final List<ViolationTicket> tickets = new ArrayList<>();

    @Override
    public long count() {
        return tickets.size();
    }

    @Override
    public ViolationTicket save(ViolationTicket ticket) {
        ViolationTicket existing = findById(ticket.getId());
        if (existing != null) {
            tickets.remove(existing);
        }
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public ViolationTicket findById(int id) {
        for (ViolationTicket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void del(int id) {
        tickets.removeIf(ticket -> ticket.getId()==id);
    }

    @Override
    public boolean existById(int id) {
        return findById(id) != null;
    }

}
