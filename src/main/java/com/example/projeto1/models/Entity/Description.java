package com.example.projeto1.models.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "descriptions")
public class Description {
    @Id
    private Long id;

    private String text;

    @OneToOne
    @MapsId
    @JoinColumn(name = "task_id")
    @JsonIgnore()
    private Task task;

    public Description(String text, Task task){
        this.text = text;
        this.task = task;
    }

}
