package com.example.Noteapp.backend.service;

import com.example.Noteapp.backend.entity.EditUser;
import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Component
public class HomeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    public String saveUser(UserDtls user, MultipartFile file,
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


    public String updateUser(int id, EditUser newUser){
        System.out.println("updateUser called");
        try{
            UserDtls existingUser = userRepository.findById(id).orElse(null);
//            if(existingUser==null) System.out.println("exisiting user null");
//            else System.out.println("not null");
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPassword(passwordEncode.encode(newUser.getPassword()));
            userRepository.save(existingUser);
            return "redirect:/login";
        }
        catch(Exception e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/signup";
    }
}
