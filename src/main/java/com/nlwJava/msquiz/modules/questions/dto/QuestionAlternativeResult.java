package com.nlwJava.msquiz.modules.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAlternativeResult {
    private UUID id;
    private String tech;
    private String description;
    private List<AlternativesResultDTO> alternatives;
}
