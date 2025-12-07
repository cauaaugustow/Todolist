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
@Table(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Description description;

    private Boolean done;

    @ManyToMany
    @JoinTable( name = "tasks_labels",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore()
    private User user;

    public Task(User user, String title, Boolean done, List<Label> labels) {
        this.title = title;
        this.done = done;
        this.user = user;
        this.labels = labels;
    }
}
