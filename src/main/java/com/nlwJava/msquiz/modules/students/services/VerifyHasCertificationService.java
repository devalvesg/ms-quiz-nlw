package com.nlwJava.msquiz.modules.students.services;

import com.nlwJava.msquiz.modules.questions.dto.AlternativesResultDTO;
import com.nlwJava.msquiz.modules.questions.dto.QuestionAlternativeResult;
import com.nlwJava.msquiz.modules.students.dto.VerifyDTO;
import com.nlwJava.msquiz.modules.questions.entities.Alternatives;
import com.nlwJava.msquiz.modules.questions.entities.Questions;
import com.nlwJava.msquiz.modules.students.repositories.CertificationStudentRepository;
import com.nlwJava.msquiz.modules.questions.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VerifyHasCertificationService {
    private CertificationStudentRepository certificationStudentRepository;
    private QuestionRepository questionRepository;

    public VerifyHasCertificationService(CertificationStudentRepository certificationStudentRepository, QuestionRepository questionRepository){
        this.certificationStudentRepository = certificationStudentRepository;
        this.questionRepository = questionRepository;
    }

    public boolean verifyHasCertification(VerifyDTO dto){
        var result = certificationStudentRepository.findByStudentEmailAndTechnology(dto.email(), dto.tech());
        if(!result.isEmpty()){
            return true;
        }
        return false;
    }

    public List<QuestionAlternativeResult> findByTech(String tech){
        var result = questionRepository.findByTech(tech);

        var toMap = result.stream().map(questions -> mapQuestionToDTO(questions)).collect(Collectors.toList());
        return toMap;
    }

    static QuestionAlternativeResult mapQuestionToDTO(Questions question){
        var questionResultDTO = QuestionAlternativeResult.builder()
                .id(question.getId())
                .tech(question.getTech())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOS = question.getAlternativesList().stream().map(alternatives -> mapAlternative(alternatives)).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOS);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternative(Alternatives alternatives){
        return AlternativesResultDTO.builder()
                .id(alternatives.getId())
                .description(alternatives.getDescription()).build();
    }
}
