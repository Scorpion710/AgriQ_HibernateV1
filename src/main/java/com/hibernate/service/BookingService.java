package com.hibernate.service;

import com.hibernate.entity.Booking;
import com.hibernate.entity.ProcurementCentre;
import com.hibernate.repository.BookingRepository;
import com.hibernate.repository.ProcurementCentreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ProcurementCentreRepository procurementCentreRepository;

    public BookingService(BookingRepository bookingRepository,
                          ProcurementCentreRepository procurementCentreRepository) {
        this.bookingRepository = bookingRepository;
        this.procurementCentreRepository = procurementCentreRepository;
    }

    public Booking createBooking(Booking booking) {
        booking.setStatus("BOOKED");
        long nextTokenNumber = bookingRepository
                .findTopByCentreIdAndBookingDateOrderByTokenNumberDesc(booking.getCentreId(), booking.getBookingDate())
                .map(Booking::getTokenNumber)
                .filter(tokenNumber -> tokenNumber != null)
                .map(tokenNumber -> tokenNumber + 1)
                .orElse(1L);
        booking.setTokenNumber(nextTokenNumber);
        booking.setQueueStatus("WAITING");
        return bookingRepository.save(booking);
    }

    public List<Booking> getFarmerBookings(Long farmerId) {
        return bookingRepository.findByFarmerIdOrderByIdDesc(farmerId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getTodayCentreQueue(Long centreId) {
        return bookingRepository.findByCentreIdAndBookingDateOrderByTokenNumberAsc(centreId, LocalDate.now().toString());
    }

    public Booking updateQueueStatus(Long id, String queueStatus) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        booking.setQueueStatus(normalizeQueueStatus(queueStatus));
        return bookingRepository.save(booking);
    }

    public List<ProcurementCentre> getProcurementCentres() {
        if (procurementCentreRepository.count() == 0) {
            procurementCentreRepository.saveAll(List.of(
                    new ProcurementCentre("AgriQ Procurement Centre - North", "North Market Yard"),
                    new ProcurementCentre("AgriQ Procurement Centre - Central", "Central Agricultural Market"),
                    new ProcurementCentre("AgriQ Procurement Centre - South", "South Village Road")
            ));
        }
        return procurementCentreRepository.findAll();
    }

    private String normalizeQueueStatus(String queueStatus) {
        if (queueStatus == null) {
            throw new RuntimeException("Queue status is required.");
        }
        String normalizedStatus = queueStatus.trim().toUpperCase();
        if (!normalizedStatus.equals("WAITING")
                && !normalizedStatus.equals("SERVING")
                && !normalizedStatus.equals("COMPLETED")) {
            throw new RuntimeException("Queue status must be WAITING, SERVING, or COMPLETED.");
        }
        return normalizedStatus;
    }
}
