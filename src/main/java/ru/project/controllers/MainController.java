package ru.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
}