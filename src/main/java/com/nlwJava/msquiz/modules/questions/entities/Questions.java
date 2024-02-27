package com.nlwJava.msquiz.modules.questions.entities;

import com.nlwJava.msquiz.modules.questions.entities.Alternatives;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "tb_questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String tech;

    private String description;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Alternatives> alternativesList;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
