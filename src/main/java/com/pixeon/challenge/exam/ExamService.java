package com.pixeon.challenge.exam;

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
    ResponseEntity<Exam> searchExam(@RequestParam() int identifier, String institutionCnpj) {

        Exam exam = repository.findExam(identifier);
        // TODO: Inform why exam can't be retrieved
        if (!exam.getInstitutionCNPJ().equals(institutionCnpj)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exam, HttpStatus.OK);
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
