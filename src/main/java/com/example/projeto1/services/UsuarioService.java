package com.example.projeto1.services;


import com.example.projeto1.models.entities.Usuario;
import com.example.projeto1.models.dto.UsuarioDTO;
import com.example.projeto1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario getUsuario(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            throw new RuntimeException("Usuario não encontrado");
        }
        return usuarioOptional.get();

    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public void createUsuario(UsuarioDTO usuarioDTO){
        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        usuarioRepository.save(novoUsuario);
    }
}
