package com.example.demo.repositories;

import com.example.demo.entities.InternshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends JpaRepository<InternshipEntity, Long> {
}
