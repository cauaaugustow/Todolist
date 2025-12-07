
package com.example.projeto1.repositories;

import com.example.projeto1.models.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
}

