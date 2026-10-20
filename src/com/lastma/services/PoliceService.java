package com.lastma.services;

import com.lastma.dtos.responses.FindPoliceResponses;

public interface PoliceService {

    public void register(String fullName, String email, String password);

    FindPoliceResponses findPoliceById(int id);

//
//    public void findTicket(String ticketNumber);
}
