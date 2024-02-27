package com.nlwJava.msquiz.modules.students.repositories;

import com.nlwJava.msquiz.modules.students.entities.Certifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificationStudentRepository extends JpaRepository<Certifications, UUID> {

    @Query("SELECT c FROM tb_certifications c INNER JOIN c.students std WHERE std.email = :email AND c.tech = :tech")
    List<Certifications> findByStudentEmailAndTechnology(String email, String tech);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_certifications ORDER BY grade DESC LIMIT 10")
    List<Certifications> findTop10ByOrderByGradeDesc();
}
