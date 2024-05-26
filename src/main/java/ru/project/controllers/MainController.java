package ru.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.models.Property;
import ru.project.models.User;
import ru.project.repo.ProperiesRepository;
import ru.project.repo.UserRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@Controller
public class MainController {

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
    public String login(Model model){
        model.addAttribute("title","пользователь");
        return  "login";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("title","пользователь");
        return  "registration";
    }

    @PostMapping("/registration")
    public String registrationUserAdd(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model){
        User user = new User(username, password, email);
        if (user.getUsername().isEmpty() | user.getPassword().isEmpty() | user.getEmail().isEmpty()) {
            return "redirect:/error";
        }
        else {
            userRepository.save(user);
            return "redirect:/list";
        }
    }

    @GetMapping("/list")
    public String list (Model model){
        model.addAttribute("title","пользователь");
        Iterable<Property> properties = properiesRepository.findAll();
        model.addAttribute("properties", properties);
        return "list_of_properties";
    }
    @GetMapping("/list/add")
    public String listAdd(Model model){
        return "list_of_properties-add";
    }

    @PostMapping("/list/add")
    public String listPostAdd(@RequestParam String title, @RequestParam String full_text, @RequestParam String image_url, Model model){
        Property property = new Property(title, full_text, image_url);
        properiesRepository.save(property);
        return "redirect:/list";
    }

    @GetMapping("/list/{id}")
    public String listDetails(@PathVariable(value = "id") long id, Model model){
        Optional<Property> property = properiesRepository.findById(id);
        ArrayList<Property> res = new ArrayList<>();
        property.ifPresent(res::add);
        model.addAttribute("property", res);
        return "property_details";
    }

}
