
package com.example.projeto1.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.projeto1.models.EntityDTO.LoginRequest;
import com.example.projeto1.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody @Valid LoginRequest user) {
        return userServices.login(user);
    }
}

