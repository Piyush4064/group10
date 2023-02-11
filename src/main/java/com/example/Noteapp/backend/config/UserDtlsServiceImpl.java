package com.example.Noteapp.backend.config;

import com.example.Noteapp.backend.entity.UserDtls;
import com.example.Noteapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDtlsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    public UserDtlsServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDtls user=userRepo.findByEmail(username);

        if(user==null)
        {
            throw new UsernameNotFoundException("User Not Exist");
        }else
        {
            CustomUserDtls customUserDtls =new CustomUserDtls(user);
            return customUserDtls;
        }


    }

}