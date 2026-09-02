package com.hibernate.controller;

import com.hibernate.entity.Booking;
import com.hibernate.entity.ProcurementCentre;
import com.hibernate.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/api/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/api/bookings/farmer/{farmerId}")
    public List<Booking> getFarmerBookings(@PathVariable Long farmerId) {
        return bookingService.getFarmerBookings(farmerId);
    }

    @GetMapping("/api/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/api/bookings/centres")
    public List<ProcurementCentre> getProcurementCentres() {
        return bookingService.getProcurementCentres();
    }

    @GetMapping("/api/queue/{centreId}")
    public List<Booking> getTodayCentreQueue(@PathVariable Long centreId) {
        return bookingService.getTodayCentreQueue(centreId);
    }

    @PutMapping("/api/bookings/{id}/queue-status")
    public Booking updateQueueStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return bookingService.updateQueueStatus(id, request.get("queueStatus"));
    }
}
