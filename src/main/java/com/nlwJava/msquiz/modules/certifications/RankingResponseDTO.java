package com.nlwJava.msquiz.modules.certifications;

import lombok.Builder;

@Builder
public record RankingResponseDTO(String email, int grade) {
}
