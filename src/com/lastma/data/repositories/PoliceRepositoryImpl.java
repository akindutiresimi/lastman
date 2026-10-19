package com.lastma.data.repositories;

import com.lastma.data.models.Police;

import java.util.ArrayList;
import java.util.List;

public class PoliceRepositoryImpl implements PoliceRepository {
    private static List<Police> polices = new ArrayList<>();
    private int numberOfPolices = 0;

    @Override
    public long count() {
        return polices.size();
    }

    @Override
    public Police save(Police police) {
        police.setId(++numberOfPolices);
        polices.add(police);
        return police;
    }

    @Override
    public Police findById(int id) {
        for (Police police : polices) {
            if (police.getId() == id) {
                return police;
            }
        }
        throw new IllegalArgumentException("Police with id " + id + " not found");
    }

    @Override
    public void del(int id) {
        Police police = findById(id);
        polices.remove(police);
    }

    @Override
    public boolean existById(int policeId) {
        for (Police police : polices) {
            if (police.getId() == policeId) {
                return true;
            }
        }
        return false;
    }

    public void deleteAll() {
        polices.clear();
        numberOfPolices = 0;
    }

    @Override
    public Police findByEmail(String email) {
        for (Police police : polices){
            if (police.getEmail().equals(email)){
                return police;
            }
        }
        return null;
    }
}