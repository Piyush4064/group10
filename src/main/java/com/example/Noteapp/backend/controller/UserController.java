package com.example.Noteapp.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/home1")
    public String home(){
        return "home1";
    }

}
