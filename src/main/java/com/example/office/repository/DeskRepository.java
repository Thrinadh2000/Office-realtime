package com.example.office.repository;

import com.example.office.model.Desk;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DeskRepository {

    // In-memory store: id -> Desk. Swap this out for JPA/Mongo later without
    // touching the Service or Controller layers.
    private final Map<String, Desk> desks = new ConcurrentHashMap<>();

    public DeskRepository() {
        seed();
    }

    private void seed() {
        save(new Desk("D1", "Desk 1", "Room A", Desk.Status.AVAILABLE));
        save(new Desk("D2", "Desk 2", "Room A", Desk.Status.AVAILABLE));
        save(new Desk("D3", "Desk 3", "Room B", Desk.Status.AVAILABLE));
    }

    public List<Desk> findAll() {
        return List.copyOf(desks.values());
    }

    public Optional<Desk> findById(String id) {
        return Optional.ofNullable(desks.get(id));
    }

    public Desk save(Desk desk) {
        desks.put(desk.getId(), desk);
        return desk;
    }
}
