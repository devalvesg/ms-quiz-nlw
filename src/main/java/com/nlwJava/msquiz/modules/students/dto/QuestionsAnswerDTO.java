package com.nlwJava.msquiz.modules.students.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class QuestionsAnswerDTO {
    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect;
}
