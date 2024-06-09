package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.DTO.UserDTO;
import ru.bookingService.config.MyUserDetails;
import ru.bookingService.entities.MyUser;
import ru.bookingService.entities.Property;
import ru.bookingService.repository.UserRepository;
import ru.bookingService.services.AppService;
import ru.bookingService.services.MyUserService;
import ru.bookingService.services.PropertyService;

import java.util.Date;


@Controller
public class BookingController {

        @Autowired
        private PropertyService propertyService;

        @Autowired
        private MyUserService myUserService;

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
        // MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        MyUser user = myUserService.getUserById(userDetails.getId());
        propertyService.bookProperty(property, user, startDate, endDate);
        return "redirect:/list";
    }
    }