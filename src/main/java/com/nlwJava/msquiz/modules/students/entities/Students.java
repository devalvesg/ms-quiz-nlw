package com.nlwJava.msquiz.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nlwJava.msquiz.modules.students.entities.Certifications;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "tb_students")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Students {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "students")
    @JsonBackReference
    private List<Certifications> certifications;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
