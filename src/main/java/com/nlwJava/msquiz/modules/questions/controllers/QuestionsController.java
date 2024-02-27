package com.nlwJava.msquiz.modules.questions.controllers;

import com.nlwJava.msquiz.modules.questions.dto.QuestionAlternativeResult;
import com.nlwJava.msquiz.modules.students.services.VerifyHasCertificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private VerifyHasCertificationService verifyHasCertificationService;

    public QuestionsController(VerifyHasCertificationService verifyHasCertificationService){
        this.verifyHasCertificationService = verifyHasCertificationService;
    }

    @GetMapping("/technology/{tech}")
    public List<QuestionAlternativeResult> findByTech(@PathVariable String tech){
        return verifyHasCertificationService.findByTech(tech);
    }


}
