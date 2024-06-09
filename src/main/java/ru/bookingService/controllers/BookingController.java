package ru.bookingService.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.models.Property;
import ru.bookingService.models.PropertyBooking;
import ru.bookingService.services.PropertyService;

import java.util.Date;

@Controller
public class BookingController {

        @Autowired
        private PropertyService propertyService;

        @GetMapping("/list/{id}/booking_form")
        public String booking(Model model) {model.addAttribute("title", "пользователь");
            return "booking_form";
        }

        @PostMapping("/list/{id}/booking_form")
        public String bookProperty(@PathVariable(value = "id") Long id, @RequestParam Date startDate, @RequestParam Date endDate) {
            Property property = propertyService.getPropertyById(id);
            propertyService.bookProperty(property, startDate, endDate);
            return "{\"message\":\"Property booked successfully\"}";

        }
    }