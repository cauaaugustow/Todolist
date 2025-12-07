package com.example.projeto1.repositories;

import com.example.projeto1.models.Entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptionRepository extends JpaRepository<Description,Long> {
}
