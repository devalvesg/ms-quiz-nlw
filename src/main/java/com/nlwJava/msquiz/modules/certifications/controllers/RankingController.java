package com.nlwJava.msquiz.modules.certifications.controllers;

import com.nlwJava.msquiz.modules.certifications.RankingResponseDTO;
import com.nlwJava.msquiz.modules.certifications.services.Top10RankingService;
import com.nlwJava.msquiz.modules.students.entities.Certifications;
import com.nlwJava.msquiz.modules.students.repositories.CertificationStudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    public RankingController(Top10RankingService top10RankingService) {
        this.top10RankingService = top10RankingService;
    }
    private Top10RankingService top10RankingService;

    @GetMapping("/top10")
    public ResponseEntity ranking(){
        List<RankingResponseDTO> top10 = top10RankingService.execute();
        return ResponseEntity.ok().body(top10);
    }
}
