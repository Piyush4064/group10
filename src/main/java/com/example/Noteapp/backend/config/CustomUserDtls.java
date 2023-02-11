package com.example.Noteapp.backend.config;

import com.example.Noteapp.backend.entity.UserDtls;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDtls implements UserDetails {

    private UserDtls userdtls;

    public CustomUserDtls(UserDtls userdtls) {
        super();
        this.userdtls = userdtls;
    }

    public CustomUserDtls() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userdtls.getProfile());

        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return userdtls.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userdtls.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
