package com.lastma.data.repositories;

import com.lastma.data.models.Police;

public interface PoliceRepository {
    long count();
    Police save(Police police);
    Police findById(int id);
    void del(int id);
    boolean existById(int policeId);
}
