package com.example.projeto1.repositories;

import com.example.projeto1.models.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmail(String email);


    Optional<User> findUserByEmailAndPassword(String email, String password);
}
