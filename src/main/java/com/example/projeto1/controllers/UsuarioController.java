package com.example.projeto1.controllers;

import com.example.projeto1.models.dto.UsuarioDTO;
import com.example.projeto1.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.status(201).build(); //201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id) {
        return usuarioService.retornoUsuario(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

}
