package com.pixeon.challenge.exam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public final class ExamService {

    private final ExamRepository repository;
    private final ExamValidator validator;
    private final DataStore dataStore;

    public ExamService(ExamRepository repository, ExamValidator validator, DataStore dataStore) {
        this.repository = repository;
        this.validator = validator;
        this.dataStore = dataStore;
    }


    @GetMapping("exam/find")
    ResponseEntity<Exam> searchExam(@RequestParam() int identifier, String institutionCnpj) {
        Exam exam = repository.getExamById(identifier);
        // TODO: Inform why exam can't be retrieved

        if (!exam.getInstitutionCNPJ().equals(institutionCnpj)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>( exam, HttpStatus.OK);
    }


    @PutMapping("exam/update")
    public Exam updateExam(@RequestBody() Exam exam) {

        exam = repository.updateExam(exam);
        return exam;
    }

    @PostMapping("exam/create")
    public String createExam(@RequestBody() Exam exam) {

        HealthCareInstitutionDomain healthCareInstitutionDomain = repository.getByCNPJ(exam.getInstitutionCNPJ());

        if (healthCareInstitutionDomain == null) {
            return "Health Care Institution not found in database";
        }

        ExamDomain examDomain = repository.getExamDomain(exam, healthCareInstitutionDomain);

        if (!validator.validate(examDomain)){
            return "Invalid exam";
        }

        if (repository.save(examDomain)) {
            return "Saved successfully";
        }

        return "Unable to input exam in database";
    }
}
