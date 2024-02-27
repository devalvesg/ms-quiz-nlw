package com.nlwJava.msquiz.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_certifications")
public class Certifications {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID student_id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonBackReference
    private Students students;

    private String tech;

    private int grade;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answers")
    @JsonManagedReference
    private List<AnswersCertification> answersCertificationList;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
