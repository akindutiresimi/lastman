package com.lastma.services;

import com.lastma.data.models.Police;
import com.lastma.data.repositories.PoliceRepository;
import com.lastma.data.repositories.PoliceRepositoryImpl;

public class PoliceServiceImpl implements PoliceService {

    private PoliceRepository policeRepository = new PoliceRepositoryImpl();

    @Override
    public void register(String fullName, String email, String password) {
        if (policeRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("This email already exist");
        }
        Police newPolice = new Police();
        newPolice.setFullName(fullName);
        newPolice.setEmail(email);
        newPolice.setPassword(password);
        policeRepository.save(newPolice);
    }

    @Override
    public void findTicket(String ticketNumber) {

    }


}
