package com.vcube.VehicleServiceApplication.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcube.VehicleServiceApplication.entity.Booking;
import com.vcube.VehicleServiceApplication.service.BookingService;

@Controller
public class BookingController {
	
	 private BookingService bookingService;
	 
	 public BookingController(BookingService bookingService){
	        this.bookingService=bookingService;
	    }
	 
	 
	 @GetMapping("/")
	 public String viewHomePage(Model model) {
		 
	        return findPaginated(1,model);
		 
	 }
	 
	 @GetMapping("/page/{pageNo}")
	 public String findPaginated(@PathVariable int pageNo, Model model) {

	     int pageSize = 5;

	     Page<Booking> page = bookingService.findPaginated(pageNo, pageSize);

	     List<Booking> listBookings = page.getContent();

	     model.addAttribute("currentPage", pageNo);
	     model.addAttribute("totalPages", page.getTotalPages());
	     model.addAttribute("listBookings", listBookings);

	     return "index";
	 }
	 
	 @GetMapping("/search")
	 public String search(@RequestParam("keyword") String keyword, Model model) {

	     List<Booking> listBookings = bookingService.searchByVehicleNumber(keyword);

	     model.addAttribute("listBookings", listBookings);
	     
	  // Prevent Thymeleaf pagination error
	     model.addAttribute("totalPages", 0);
	     model.addAttribute("currentPage", 1);

	     return "index";
	 }
	 
	 
	 
	 
	 
	 @GetMapping("/showNewBookingForm")
	 public String ShowNewBookingForm(Model model) {
		 Booking booking = new Booking();
		 model.addAttribute("booking",booking);
		 return "add-booking";
	 }
	 
	 @PostMapping("/saveBooking")
	    public String saveBooking(@ModelAttribute("booking") Booking booking){
	        bookingService.saveBooking(booking);
	        return "redirect:/";
	    }
	 
	  @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable Long id,Model model){
	        Booking booking=bookingService.getBookingById(id);
	        model.addAttribute("booking",booking);
	        return "edit-booking";
	    }
	  
	   @GetMapping("/deleteBooking/{id}")
	    public String deleteBooking(@PathVariable Long id){
	        bookingService.deleteBooking(id);
	        return "redirect:/";
	    }

	 
	 
	
	

}
