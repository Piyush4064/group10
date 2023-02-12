package com.example.Noteapp.backend.repository;
import com.example.Noteapp.backend.entity.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDtls,Integer> {
    UserDtls findByEmail(String email);
}
