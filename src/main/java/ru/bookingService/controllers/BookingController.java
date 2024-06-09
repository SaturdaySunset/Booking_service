package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.models.Property;
import ru.bookingService.models.PropertyBooking;
import ru.bookingService.services.PropertyService;

@Controller
public class BookingController {

        @Autowired
        private PropertyService propertyService;

        @PostMapping("/list/{id}/booking_form")
        public String bookProperty(@PathVariable(value = "id") Long id, @RequestBody PropertyBooking propertyBooking) {
            Property property = propertyService.getPropertyById(id);
            propertyService.bookProperty(property, propertyBooking.getStartDate(), propertyBooking.getEndDate());
            return "{\"message\":\"Property booked successfully\"}";

        }
    }