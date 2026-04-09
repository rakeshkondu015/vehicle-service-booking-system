package com.vcube.VehicleServiceApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.vcube.VehicleServiceApplication.entity.Booking;

public interface BookingService {

	
	List<Booking> getAllBookings();

    Booking saveBooking(Booking booking);

    Booking getBookingById(Long id);

    Booking updateBooking(Booking booking);

    void deleteBooking(Long id);
    
    Page<Booking> findPaginated(int pageNo, int pageSize);
    
    List<Booking> searchByVehicleNumber(String keyword);
}
