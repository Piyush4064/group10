package com.example.Noteapp.backend.controller;

import com.example.Noteapp.backend.entity.EditUser;
import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.Noteapp.backend.service.NoteService;

import javax.servlet.http.HttpSession;




@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("profileImage") MultipartFile file,
                           HttpSession session) {

        return homeService.saveUser(user,file,session);
    }

    @PutMapping("/updateUser")
    public  String updateUser(@RequestParam int id, @RequestBody EditUser newUser){
        return homeService.updateUser(id,newUser);

    }


}