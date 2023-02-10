package com.example.Noteapp.backend.controller;
import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, HttpSession session){

     userRepository.save(user);
     System.out.println(user);

        return "redirect:/signup";
    }

}
