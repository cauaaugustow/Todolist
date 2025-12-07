package com.example.projeto1.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.projeto1.models.EntityDTO.TaskCreateRequest;
import com.example.projeto1.models.EntityDTO.TaskDTO;
import com.example.projeto1.services.TaskServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServices taskServices;

    @GetMapping("/{idTask}")
    public ResponseEntity<?> getTaskById(@PathVariable Long idTask){
        return taskServices.findTaskByID(idTask);
    }


    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(required = false) Long userId){
        return taskServices.getAllTasks(userId);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<?> createTask(@PathVariable Long idUser, @RequestBody @Valid TaskCreateRequest taskDTO){
        return taskServices.createTask(idUser, taskDTO);
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable Long idTask){
        taskServices.deleteTask(idTask);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<?> updateTask(@PathVariable Long idTask, @RequestBody @Valid TaskCreateRequest taskDTO){
        taskServices.updateTask(taskDTO, idTask);
        return ResponseEntity.ok().build();
    }

}
