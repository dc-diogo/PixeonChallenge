package com.pixeon.challenge.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping("exam/create")
    public String createExam(@RequestBody() Exam exam) {

        ExamDomain examDomain = repository.getExamDomain(exam);
        if (examDomain == null) return "Health Care Institution not found in database";
        if (!validator.validate(examDomain)) return "Invalid exam";

        return repository.saveExam(examDomain);
    }

    @GetMapping("exam/find")
    ResponseEntity<String> searchExam(@RequestParam() int identifier, String institutionCnpj) {

        Exam exam = repository.findExam(identifier);
        if (exam.equals(null)){
            return new ResponseEntity<>("Exam not found.", HttpStatus.BAD_REQUEST);
        }

        if (!exam.getInstitutionCNPJ().equals(institutionCnpj)) {
            return new ResponseEntity<>("This Institution does not have permission to access this exam.", HttpStatus.FORBIDDEN);
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            return new ResponseEntity<>(mapper.writeValueAsString(exam), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Unexpected error", HttpStatus.NOT_FOUND);

    }

    @PutMapping("exam/update")
    public Exam updateExam(@RequestBody() Exam exam) {
        exam = repository.updateExam(exam);
        return exam;
    }

    @DeleteMapping("exam/delete")
    public String deleteExam(@RequestParam() int identifier, @RequestParam(name = "cnpj") String hcInstitutionCnpj) {
        return repository.deleteExam(identifier, hcInstitutionCnpj);
    }


}
