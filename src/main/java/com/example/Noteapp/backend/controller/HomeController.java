package com.example.Noteapp.backend.controller;

import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

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

        try {
            user.setPassword(passwordEncode.encode(user.getPassword()));
            user.setProfile("USER");

            // processing and uploading profile image
            if (file.isEmpty()) {
                // handle if file is empty
                System.out.println("No image is selected.");
            } else {
                user.setProfile_img(file.getOriginalFilename());
//                System.out.println(new ClassPathResource("").getFile().getAbsolutePath());
                File newFile = new ClassPathResource("./static/img").getFile();
                Path path = Paths.get(newFile.getAbsolutePath() + "-" + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image is uploaded.");

                UserDtls newUser = userRepository.save(user);
            }
            return "redirect:/login";

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/signup";
    }
}