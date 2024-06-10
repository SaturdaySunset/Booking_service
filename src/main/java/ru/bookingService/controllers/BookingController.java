package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.config.MyUserDetails;
import ru.bookingService.entities.Booking;
import ru.bookingService.entities.MyUser;
import ru.bookingService.entities.Property;
import ru.bookingService.services.BookingService;
import ru.bookingService.services.MyUserService;
import ru.bookingService.services.PropertyService;

import java.util.Date;
import java.util.List;


@Controller
public class BookingController {

        @Autowired
        private PropertyService propertyService;

        @Autowired
        private MyUserService myUserService;
        @Autowired
        private BookingService bookingService;

        @GetMapping("/list/{id}/booking_form")
        public String booking(Model model, @PathVariable(value = "id") Long id) {
            model.addAttribute("title", "пользователь");
            model.addAttribute("property", propertyService.getPropertyById(id));
            return "booking_form";
        }

    @PostMapping("/list/{id}/booking_form")
    public String bookProperty(@PathVariable(value = "id") Long id,
                               @AuthenticationPrincipal MyUserDetails userDetails,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Property property = propertyService.getPropertyById(id);
        MyUser user = myUserService.getUserById(userDetails.getId());
        propertyService.bookProperty(property, user, startDate, endDate);
        return "redirect:/list";
    }
    @GetMapping("/bookings")
    public String getBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "all_bookings";
    }
    }