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
        ViolationTicket existing = findById(ticket.getPlateNumber());
        if (existing != null) {
            tickets.remove(existing);
        }
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public ViolationTicket findById(String id) {
        for (ViolationTicket ticket : tickets) {
            if (ticket.getPlateNumber().equals(id)) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public void del(String id) {
        tickets.removeIf(ticket -> ticket.getPlateNumber().equals(id));
    }

    @Override
    public boolean existById(String id) {
        return findById(id) != null;
    }

}
