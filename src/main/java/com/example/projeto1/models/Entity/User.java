package com.example.projeto1.models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class User {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            private String name;
            private String lastname;
            private String cpf;

            @JsonIgnore
            private String email;
            @JsonIgnore
            private String password;

            @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
            private List<Task> tasks;

            public User(String name, String lastname, String cpf, String email, String password) {
                this.name = name;
                this.lastname = lastname;
                this.cpf = cpf;
                this.email = email;
                this.password = password;
            }
}


