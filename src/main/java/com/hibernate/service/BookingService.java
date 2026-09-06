package com.hibernate.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hibernate.entity.Booking;
import com.hibernate.entity.ProcurementCentre;
import com.hibernate.repository.BookingRepository;
import com.hibernate.repository.ProcurementCentreRepository;

import jakarta.annotation.PostConstruct;

@Service
public class BookingService {

    public static final List<ProcurementCentre> PUNE_CENTRES = List.of(
            new ProcurementCentre("Shri Chhatrapati Shivaji Maharaj Market Yard, Gultekdi", "Gultekdi, Pune"),
            new ProcurementCentre("Manjari Sub-Market Yard", "Manjari, Pune"),
            new ProcurementCentre("Shri Nageshwar Maharaj Sub-Market Yard, Moshi", "Moshi, Pune"),
            new ProcurementCentre("Pimpri Sub-Market Yard", "Pimpri, Pune"),
            new ProcurementCentre("Khadki Sub-Market Yard", "Khadki, Pune"),
            new ProcurementCentre("Uttamnagar Sub-Market Yard", "Uttamnagar, Pune")
    );

    private final BookingRepository bookingRepository;
    private final ProcurementCentreRepository procurementCentreRepository;

    public BookingService(BookingRepository bookingRepository,
                          ProcurementCentreRepository procurementCentreRepository) {
        this.bookingRepository = bookingRepository;
        this.procurementCentreRepository = procurementCentreRepository;
    }

    @PostConstruct
    public void initProcurementCentres() {
        syncProcurementCentres();
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

    public synchronized List<ProcurementCentre> getProcurementCentres() {
        return syncProcurementCentres();
    }

    private synchronized List<ProcurementCentre> syncProcurementCentres() {
        List<ProcurementCentre> existing = procurementCentreRepository.findAll();
        boolean hasLegacy = existing.stream().anyMatch(c -> c.getName() != null
                && (c.getName().contains("North") || c.getName().contains("South") || c.getName().contains("AgriQ Procurement Centre")));
        boolean countMismatch = existing.size() < PUNE_CENTRES.size();

        if (existing.isEmpty() || hasLegacy || countMismatch) {
            for (int i = 0; i < PUNE_CENTRES.size(); i++) {
                ProcurementCentre puneCentre = PUNE_CENTRES.get(i);
                if (i < existing.size()) {
                    ProcurementCentre current = existing.get(i);
                    current.setName(puneCentre.getName());
                    current.setLocation(puneCentre.getLocation());
                    procurementCentreRepository.save(current);
                } else {
                    procurementCentreRepository.save(new ProcurementCentre(puneCentre.getName(), puneCentre.getLocation()));
                }
            }
            if (existing.size() > PUNE_CENTRES.size()) {
                for (int i = PUNE_CENTRES.size(); i < existing.size(); i++) {
                    procurementCentreRepository.delete(existing.get(i));
                }
            }
            return procurementCentreRepository.findAll();
        }
        return existing;
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
