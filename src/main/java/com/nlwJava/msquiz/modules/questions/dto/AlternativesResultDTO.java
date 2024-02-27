package com.nlwJava.msquiz.modules.questions.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AlternativesResultDTO {
    private UUID id;
    private String description;
}
