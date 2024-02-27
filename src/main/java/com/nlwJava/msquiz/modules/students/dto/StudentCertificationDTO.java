package com.nlwJava.msquiz.modules.students.dto;

import com.nlwJava.msquiz.modules.students.dto.QuestionsAnswerDTO;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCertificationDTO {

    private String email;
    private String tech;
    private List<QuestionsAnswerDTO> questionsAnswer;
}
