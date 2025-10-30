package com.example.projeto1.services;


import com.example.projeto1.models.dto.UsuarioDTO;
import com.example.projeto1.models.dto.UsuarioPublicoDTO;
import com.example.projeto1.models.entities.Usuario;
import com.example.projeto1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional <Usuario> findUsuarioUById(Long id) {
        return usuarioRepository.findById(id);

    }

    private UsuarioPublicoDTO criarUsuarioPublico(Usuario usuario){
        UsuarioPublicoDTO usuarioPublicoDTO = new UsuarioPublicoDTO(usuario.getId(), usuario.getNome(), usuario.getSenha());
        return usuarioPublicoDTO;
    }

    public ResponseEntity<?> retornoUsuario(Long id){
        Optional<Usuario> usuarioEncontrado = findUsuarioUById(id);
        return usuarioEncontrado.isEmpty()
                ? ResponseEntity.badRequest().body("Usuario não encontrado")
                : ResponseEntity.ok(usuarioEncontrado.get());
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public void createUsuario(UsuarioDTO usuarioDTO){
        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        usuarioRepository.save(novoUsuario);
    }
}
