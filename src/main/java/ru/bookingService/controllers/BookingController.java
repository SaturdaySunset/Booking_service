package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.entities.Property;
import ru.bookingService.services.PropertyService;

import java.util.Date;

@Controller
public class BookingController {

        @Autowired
        private PropertyService propertyService;

        @GetMapping("/list/{id}/booking_form")
        public String booking(Model model, @PathVariable(value = "id") Long id) {
            model.addAttribute("title", "пользователь");
            model.addAttribute("property", propertyService.getPropertyById(id));
            return "booking_form";
        }

    @PostMapping("/list/{id}/booking_form")
    public String bookProperty(@PathVariable(value = "id") Long id,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Property property = propertyService.getPropertyById(id);
        propertyService.bookProperty(property, startDate, endDate);
        return "redirect:/list";
    }
    }