
package com.example.projeto1.repositories;

import com.example.projeto1.models.Entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}

