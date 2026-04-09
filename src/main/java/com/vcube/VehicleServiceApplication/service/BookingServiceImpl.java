package com.vcube.VehicleServiceApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vcube.VehicleServiceApplication.entity.Booking;
import com.vcube.VehicleServiceApplication.repository.BookingRepository;


@Service
public class BookingServiceImpl implements BookingService {

	
	
private BookingRepository bookingRepository;

public BookingServiceImpl(BookingRepository bookingRepository) {
	this.bookingRepository = bookingRepository;
}
	
	
	
	
	
	@Override
	public List<Booking> getAllBookings() {
		
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBookingById(Long id) {
		
		return bookingRepository.findById(id).get();
	}

	@Override
	public Booking updateBooking(Booking booking) {
		
		return bookingRepository.save(booking);
	}

	@Override
	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
		
	}





	@Override
	public Page<Booking> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return bookingRepository.findAll(pageable);
	}





	@Override
	public List<Booking> searchByVehicleNumber(String keyword) {
	    return bookingRepository.findByVehicleNumberContaining(keyword);
	}
}
