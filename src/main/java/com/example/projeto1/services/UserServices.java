package com.example.projeto1.services;

import lombok.RequiredArgsConstructor;
import com.example.projeto1.models.Entity.User;
import com.example.projeto1.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public Optional<User> searchUserById(Long idUser){
        return userRepository.findById(idUser);
    }

    public Optional<User> searchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    // -----------------------------------------------------------------------------------------------------------------

    public com.example.projeto1.models.EntityDTO.UserDTO createUserDTO(User user){
        return new com.example.projeto1.models.EntityDTO.UserDTO(user.getId(), user.getName(), user.getLastname(), user.getCpf(), user.getEmail(), user.getPassword(), user.getTasks());

    }

    public com.example.projeto1.models.EntityDTO.LoginUserDTO createLoginUserDTO(User user){
        return new com.example.projeto1.models.EntityDTO.LoginUserDTO(user.getId(), user.getName());
    }

    public ResponseEntity<com.example.projeto1.models.EntityDTO.UserDTO> findUserByID(Long id){
        Optional<User> foundUser = userRepository.findById(id);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        com.example.projeto1.models.EntityDTO.UserDTO userDTO = createUserDTO(foundUser.get());
        return ResponseEntity.ok().body(userDTO);
    }

    public ResponseEntity<List<com.example.projeto1.models.EntityDTO.UserDTO>> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<com.example.projeto1.models.EntityDTO.UserDTO> userDTOs = new ArrayList<>();

        for(User user : users){
            com.example.projeto1.models.EntityDTO.UserDTO userDTO = createUserDTO(user);
            userDTOs.add(userDTO);
        }
        return ResponseEntity.ok().body(userDTOs);
    }

    public ResponseEntity<?> createUser(com.example.projeto1.models.EntityDTO.UserDTO userDTO){
        User user = new User(userDTO.name(), userDTO.lastname(), userDTO.cpf(), userDTO.email(), userDTO.password());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteUser(Long idUser){
        Optional<User> user = userRepository.findById(idUser);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(idUser);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateUser(Long idUser, com.example.projeto1.models.EntityDTO.UserDTO userDTO){
        Optional<User> foundUser = userRepository.findById(idUser);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = foundUser.get();

        user.setName(userDTO.name());
        user.setLastname(userDTO.lastname());
        user.setCpf(userDTO.cpf());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public ResponseEntity<?> login(com.example.projeto1.models.EntityDTO.LoginRequest user){
        Optional<User> foundUserByEmail = userRepository.findUserByEmail(user.email());

        if(foundUserByEmail.isEmpty()){
            return ResponseEntity.status(404).body(
                    Map.of(
                            "sucess", false,
                            "message", "Usuário não encontrado."
                    )
            );
        }

        Optional<User> foundUser = userRepository.findUserByEmailAndPassword(user.email(), user.password());
        if(!foundUser.get().getPassword().equals(user.password()) || !foundUser.get().getEmail().equals(user.email())){
            return ResponseEntity.status(401).body(
                    Map.of(
                            "sucess", false,
                            "message", "Credênciais Inválidas."
                    )
            );
        }

        com.example.projeto1.models.EntityDTO.LoginUserDTO userDTO = createLoginUserDTO(foundUser.get());

        return ResponseEntity.ok(userDTO);

    }

    public ResponseEntity<?> findUserByEmail(String email){
        Optional<User> foundUser = searchUserByEmail(email);

        if(foundUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        com.example.projeto1.models.EntityDTO.UserDTO userDTO = createUserDTO(foundUser.get());
        return ResponseEntity.ok().body(userDTO);
    }
}
