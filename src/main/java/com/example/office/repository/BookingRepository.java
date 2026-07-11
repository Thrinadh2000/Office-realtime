package com.example.office.repository;

import com.example.office.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookingRepository {

    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public List<Booking> findAll() {
        return List.copyOf(bookings.values());
    }

    public Booking save(Booking booking) {
        bookings.put(booking.getId(), booking);
        return booking;
    }
}
