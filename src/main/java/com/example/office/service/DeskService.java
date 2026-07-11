package com.example.office.service;

import com.example.office.model.Desk;
import com.example.office.repository.DeskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskService {

    private final DeskRepository deskRepository;

    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    public Desk getDesk(String id) {
        return deskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Desk not found: " + id));
    }
}
