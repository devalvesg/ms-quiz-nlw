package com.nlwJava.msquiz.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tb_answers")
public class AnswersCertification {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationID;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private Certifications certifications;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Students students;

    private UUID answer_id;
    private UUID questionID;

    private boolean isCorrect;
}
