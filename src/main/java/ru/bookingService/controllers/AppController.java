package ru.bookingService.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bookingService.models.Application;
import ru.bookingService.models.MyUser;
import ru.bookingService.models.Property;
import ru.bookingService.models.UserDTO;
import ru.bookingService.repository.ProperiesRepository;
import ru.bookingService.repository.UserRepository;
import ru.bookingService.services.AppService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("booking/v1")
@AllArgsConstructor
public class AppController {
    private AppService service;
    @Autowired
    private ProperiesRepository properiesRepository;
    @Autowired
    private UserRepository userRepository;

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

    //    @PostMapping("/login")
//    public String loginCheck(@RequestParam String email, @RequestParam String password, Model model){
//
//        return  "redirect:/list";
//    }
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "пользователь");
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUserAdd(UserDTO user) {
        service.addUser(user);
        return "redirect:/list";
    }



    @GetMapping("/list")
    public String list (Model model){
        model.addAttribute("title","пользователь");
        Iterable<Property> properties = properiesRepository.findAll();
        model.addAttribute("properties", properties);
        return "list_of_properties";
    }
    @GetMapping("/list/add")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listAdd(Model model){
        return "list_of_properties-add";
    }

    @PostMapping("/list/add")
    public String listPostAdd(@RequestParam String title, @RequestParam String full_text, @RequestParam String image_url, Model model){
        Property property = new Property(title, full_text, image_url);
        properiesRepository.save(property);
        return "list";
    }

    @GetMapping("/list/{id}")
    public String listDetails(@PathVariable(value = "id") long id, Model model){
        Optional<Property> property = properiesRepository.findById(id);
        ArrayList<Property> res = new ArrayList<>();
        property.ifPresent(res::add);
        model.addAttribute("property", res);
        return "property_details";
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationByID(@PathVariable int id) {
        return service.applicationByID(id);
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("title", "пользователь");
        return "test";
    }
}
