package com.nlwJava.msquiz.modules.students.services;

import com.nlwJava.msquiz.modules.students.dto.StudentCertificationDTO;
import com.nlwJava.msquiz.modules.students.dto.VerifyDTO;
import com.nlwJava.msquiz.modules.questions.entities.Questions;
import com.nlwJava.msquiz.modules.students.entities.AnswersCertification;
import com.nlwJava.msquiz.modules.students.entities.Certifications;
import com.nlwJava.msquiz.modules.students.entities.Students;
import com.nlwJava.msquiz.modules.students.repositories.CertificationStudentRepository;
import com.nlwJava.msquiz.modules.questions.repositories.QuestionRepository;
import com.nlwJava.msquiz.modules.students.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationService {

    private StudentRepository studentRepository;
    private QuestionRepository questionRepository;
    private CertificationStudentRepository certificationStudentRepository;
    private VerifyHasCertificationService verifyHasCertificationService;

    public StudentCertificationService(StudentRepository studentRepository,
                                       QuestionRepository questionRepository,
                                       CertificationStudentRepository certificationStudentRepository,
                                       VerifyHasCertificationService verifyHasCertificationService

    ){
        this.verifyHasCertificationService = verifyHasCertificationService;
        this.certificationStudentRepository = certificationStudentRepository;
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
    }

    public Certifications execute(StudentCertificationDTO studentCertificationDTO) throws Exception {

        boolean hasCertification = this.verifyHasCertificationService.verifyHasCertification(new VerifyDTO(studentCertificationDTO.getEmail(), studentCertificationDTO.getTech()));

        if(hasCertification){
            throw new Exception("Sua certificação já foi tirada para essa tecnologia!");
        }


        List<Questions> questionsList = questionRepository.findByTech(studentCertificationDTO.getTech());
        List<AnswersCertification> answersCertificationList = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

        studentCertificationDTO.getQuestionsAnswer()
                .stream().forEach(questionAnswers -> {
                    var questionEntity = questionsList.stream().filter(question ->
                            question.getId().equals(questionAnswers.getQuestionID())).findFirst().get();

                    var correctAlternative = questionEntity.getAlternativesList().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if(correctAlternative.getId().equals(questionAnswers.getAlternativeID())){
                        questionAnswers.setCorrect(true);
                        correctAnswers.incrementAndGet();
                    }
                    else{
                        questionAnswers.setCorrect(false);
                    }

                    AnswersCertification answersEntity = AnswersCertification.builder()
                            .answer_id(questionAnswers.getAlternativeID())
                            .questionID(questionAnswers.getQuestionID())
                            .isCorrect(questionAnswers.isCorrect()).build();


                    answersCertificationList.add(answersEntity);
                });

        var student = studentRepository.findByEmail(studentCertificationDTO.getEmail());
        UUID studentId;
        if(student.isEmpty()){
            var studentCreated = Students.builder().email(studentCertificationDTO.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        }else{
            studentId = student.get().getId();
        }


        Certifications certifications = Certifications.builder()
                .tech(studentCertificationDTO.getTech())
                .student_id(studentId)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certifications);

        answersCertificationList.stream().forEach(answersCertification -> {
            answersCertification.setId(answersCertification.getAnswer_id());
            answersCertification.setCertificationID(certifications.getId());
            answersCertification.setCertifications(certifications);
        });

        certifications.setAnswersCertificationList(answersCertificationList);

        return certificationStudentCreated;

    }
}
