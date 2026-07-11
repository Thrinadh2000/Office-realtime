package com.example.office.controller;

import com.example.office.dto.BookingRequest;
import com.example.office.model.Booking;
import com.example.office.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking bookDesk(@Valid @RequestBody BookingRequest request) {

        return bookingService.bookDesk(request);
    }

    @GetMapping
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }
}
