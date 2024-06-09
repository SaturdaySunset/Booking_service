package ru.bookingService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bookingService.services.PropertyService;

@Controller

public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("list/{id}/remove")
    public ResponseEntity<String> removeProperty(@PathVariable Long id) {
        propertyService.removeProperty(id);
            return ResponseEntity.ok("list_of_properties");
    }


}