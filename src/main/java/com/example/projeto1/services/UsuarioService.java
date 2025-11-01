package com.example.projeto1.services;


import com.example.projeto1.models.dto.UsuarioDTO;
import com.example.projeto1.models.dto.UsuarioPublicoDTO;
import com.example.projeto1.models.entities.Usuario;
import com.example.projeto1.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UsuarioPublicoDTO> getAllUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioPublicoDTO> usuarioPublicos = new ArrayList<>();
        for (Usuario usuario : usuarios){
            UsuarioPublicoDTO usuarioPublicoDTO =criarUsuarioPublico(usuario);
            usuarioPublicos.add(usuarioPublicoDTO);
        }
        return usuarioPublicos;
    }

    public void createUsuario(UsuarioDTO usuarioDTO){
        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getSenha());
        usuarioRepository.save(novoUsuario);
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioPublicoDTO atualizaUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioEncontrado = findUsuarioUById(id);
        if (usuarioEncontrado.isEmpty()){
            System.out.println("Usuario não encontrado");
            throw new RuntimeException("Usuario não encontrado");
        }
        Usuario usuario = usuarioEncontrado.get();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuarioRepository.save(usuario);
        return criarUsuarioPublico(usuario);

    }
}


