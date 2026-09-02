package com.hibernate.repository;

import com.hibernate.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByFarmerIdOrderByIdDesc(Long farmerId);

    Optional<Booking> findTopByCentreIdAndBookingDateOrderByTokenNumberDesc(Long centreId, String bookingDate);

    List<Booking> findByCentreIdAndBookingDateOrderByTokenNumberAsc(Long centreId, String bookingDate);
}
