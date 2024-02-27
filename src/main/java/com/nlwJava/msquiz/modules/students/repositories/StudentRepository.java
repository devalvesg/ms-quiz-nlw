package com.nlwJava.msquiz.modules.students.repositories;

import com.nlwJava.msquiz.modules.students.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Students, UUID> {

    public Optional<Students> findByEmail(String email);
}
