package com.lastma.services;

import com.lastma.data.models.Police;
import com.lastma.data.repositories.PoliceRepository;
import com.lastma.data.repositories.PoliceRepositoryImpl;
import com.lastma.dtos.responses.FindPoliceResponses;

public class PoliceServiceImpl implements PoliceService {

    private PoliceRepository policeRepository = new PoliceRepositoryImpl();

    @Override
    public void register(String fullName, String email, String password) {
        if (policeRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("This email already exist");
        }

//        if (policeRepository.findById(id) != null){
//            throw new IllegalArgumentException("This id Is invalid, input valid id");
//        }
        Police newPolice = new Police();
        newPolice.setFullName(fullName);
        newPolice.setEmail(email);
        newPolice.setPassword(password);
        policeRepository.save(newPolice);

    }

    @Override
    public FindPoliceResponses findPoliceById(int id) {
        Police newPolice = policeRepository.findById(id);
        if(newPolice == null){
            throw  new IllegalArgumentException("Police id not find, enter valid id");

        }
        FindPoliceResponses responseDto = new FindPoliceResponses();
        responseDto.setId(newPolice.getId());
        responseDto.setFullName(newPolice.getFullName());
        responseDto.setEmail(newPolice.getEmail());
        return responseDto;
    }

}
