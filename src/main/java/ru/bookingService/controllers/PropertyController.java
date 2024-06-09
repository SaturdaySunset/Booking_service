package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.bookingService.services.PropertyService;

@Controller

public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("list/{id}")
    public String removeProperty(@PathVariable Long id) {
        propertyService.removeProperty(id);
        return "redirect:/list";
    }


}