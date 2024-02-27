package com.nlwJava.msquiz.modules.questions.repositories;

import com.nlwJava.msquiz.modules.questions.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Questions, UUID> {

    List<Questions> findByTech(String tech);
}
