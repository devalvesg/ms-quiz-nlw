package com.nlwJava.msquiz.modules.students.controllers;

import com.nlwJava.msquiz.modules.students.dto.StudentCertificationDTO;
import com.nlwJava.msquiz.modules.students.dto.VerifyDTO;
import com.nlwJava.msquiz.modules.students.services.StudentCertificationService;
import com.nlwJava.msquiz.modules.students.services.VerifyHasCertificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/certification")
public class StudentController {

    private VerifyHasCertificationService verifyHasCertificationService;

    private StudentCertificationService studentCertificationService;

    public StudentController(VerifyHasCertificationService verifyHasCertificationService, StudentCertificationService studentCertificationService){
        this.verifyHasCertificationService = verifyHasCertificationService;
        this.studentCertificationService = studentCertificationService;
    }

    @PostMapping("/verifyCertification")
    public ResponseEntity verifyIfHasCertification(@RequestBody @Valid VerifyDTO verifyDTO){
        var result = verifyHasCertificationService.verifyHasCertification(verifyDTO);
        if(result){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O usuário já realizou a prova!");
        }
        return ResponseEntity.ok().body("Usuário liberado para realizar a prova!");
    }

    @PostMapping("/answer")
    public ResponseEntity certificationDTO(@RequestBody StudentCertificationDTO studentCertificationDTO) throws Exception {
        try{
            return ResponseEntity.ok().body(studentCertificationService.execute(studentCertificationDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
