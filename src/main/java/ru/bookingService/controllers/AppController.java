package ru.bookingService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.entities.Property;
import ru.bookingService.DTO.UserDTO;
import ru.bookingService.repository.PropertyRepository;
import ru.bookingService.services.AppService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
//@RequestMapping("booking/v1")
@AllArgsConstructor
public class AppController {
    private AppService service;
    @Autowired
    private PropertyRepository properiesRepository;

    @GetMapping("/")
    public String first(Model model) {
        model.addAttribute("title", "пользователь");
        return "first";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "пользователь");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "пользователь");
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUserAdd(UserDTO user) {
        user.setRoles("USER");
        service.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("title", "пользователь");
        Iterable<Property> properties = properiesRepository.findAll();
        model.addAttribute("properties", properties);
        return "list_of_properties";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list/add")
    public String listAdd(Model model) {
        return "list_of_properties-add";
    }

    @PostMapping("/list/add")
    public String listPostAdd(@RequestParam String title, @RequestParam String full_text, @RequestParam String image_url, Model model) {
        Property property = new Property(title, full_text, image_url);
        properiesRepository.save(property);
        return "redirect:/list";
    }

    @GetMapping("/list/{id}")
    public String listDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Property> property = properiesRepository.findById(id);
        ArrayList<Property> res = new ArrayList<>();
        property.ifPresent(res::add);
        model.addAttribute("property", res);
        return "property_details";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("title", "пользователь");
        return "error";
    }
}
