package com.lastma.data.repositories;

import com.lastma.data.models.Vehicle;
import com.lastma.data.models.ViolationTicket;

public interface ViolationTicketRepository {
    long count();
    ViolationTicket save(ViolationTicket ticket);
    ViolationTicket findById(int id);
    void del(int id);
    boolean existById(int id);
}
