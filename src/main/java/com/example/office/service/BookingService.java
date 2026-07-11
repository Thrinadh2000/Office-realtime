package com.example.office.service;

import com.example.office.dto.BookingRequest;
import com.example.office.model.Booking;
import com.example.office.model.Desk;
import com.example.office.repository.BookingRepository;
import com.example.office.repository.DeskRepository;
import com.example.office.websocket.OfficeSocketHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final DeskRepository deskRepository;
    private final BookingRepository bookingRepository;
    private final OfficeSocketHandler socketHandler;

    public BookingService(DeskRepository deskRepository,
                           BookingRepository bookingRepository,
                           OfficeSocketHandler socketHandler) {
        this.deskRepository = deskRepository;
        this.bookingRepository = bookingRepository;
        this.socketHandler = socketHandler;
    }

    public synchronized Booking bookDesk(BookingRequest request) {
        Desk desk = deskRepository.findById(request.getDeskId())
                .orElseThrow(() -> new IllegalArgumentException("Desk not found: " + request.getDeskId()));

        if (desk.getStatus() == Desk.Status.BOOKED) {
            throw new IllegalStateException("Desk already booked: " + desk.getId());
        }

        desk.setStatus(Desk.Status.BOOKED);
        deskRepository.save(desk);

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                desk.getId(),
                request.getEmployeeName(),
                LocalDateTime.now()
        );
        bookingRepository.save(booking);

        // Real-time part: tell every connected client immediately.
        String message = """
                {"event":"DESK_BOOKED","deskId":"%s","employeeName":"%s","bookedAt":"%s"}
                """.formatted(desk.getId(), booking.getEmployeeName(), booking.getBookedAt());
        socketHandler.broadcast(message);

        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
