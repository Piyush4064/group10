package com.example.Noteapp.backend.repository;
import com.example.Noteapp.backend.entity.UserDtls;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDtls,Integer> {
 public UserDtls findByEmail(String email);
}
