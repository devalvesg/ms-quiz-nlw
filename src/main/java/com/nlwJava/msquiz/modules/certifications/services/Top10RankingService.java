package com.nlwJava.msquiz.modules.certifications.services;

import com.nlwJava.msquiz.modules.certifications.RankingResponseDTO;
import com.nlwJava.msquiz.modules.students.entities.Certifications;
import com.nlwJava.msquiz.modules.students.entities.Students;
import com.nlwJava.msquiz.modules.students.repositories.CertificationStudentRepository;
import com.nlwJava.msquiz.modules.students.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Top10RankingService {

    private CertificationStudentRepository certificationStudentRepository;

    private StudentRepository studentRepository;

    public Top10RankingService(CertificationStudentRepository certificationStudentRepository, StudentRepository studentRepository) {
        this.certificationStudentRepository = certificationStudentRepository;
        this.studentRepository = studentRepository;
    }

    public List<RankingResponseDTO> execute(){
        List<Certifications> certifications = this.certificationStudentRepository.findTop10ByOrderByGradeDesc();
        List<Students> students = studentRepository.findAll();
        List<RankingResponseDTO> ranking = new ArrayList<>();

        certifications.stream().forEach(certification -> {
            students.stream().forEach(student -> {
                if(student.getId() == certification.getStudent_id()){
                    var rankingResponse = RankingResponseDTO.builder()
                                    .grade(certification.getGrade())
                                    .email(student.getEmail())
                                    .build();
                    ranking.add(rankingResponse);
                }
            });
        });

        return ranking;

    }
}
