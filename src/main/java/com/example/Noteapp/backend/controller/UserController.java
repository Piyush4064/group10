package com.example.Noteapp.backend.controller;

import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home1")
    public String home(Model m){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDtls user = userRepository.findByEmail(auth.getName());
//        System.out.println(user);
        m.addAttribute("user",user);
        return "home1";
    }

}
