package com.lastma.services;

public interface PoliceService {

    public void register(String fullName, String email, String password);


    public void findTicket(String ticketNumber);
}
