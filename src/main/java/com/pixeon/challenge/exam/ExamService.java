package com.pixeon.challenge.exam;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import com.pixeon.challenge.exam.domain.ExamDomain;
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

    @GetMapping("search")
    public String searchExam(@RequestParam int identifier) {

        return "";
    }

    @PostMapping("exam/create")
    public String createExam(@RequestBody() Exam exam) {

        HealthCareInstitutionDomain healthCareInstitutionDomain = dataStore.getByName(exam.getInstitutionName());

        if (healthCareInstitutionDomain == null) {
            return "Health Care Institution not found in database";
        }

        ExamDomain examDomain = new ExamDomain(
                dataStore.getExamNextIdentifier(),
                exam.getPatientName(),
                exam.getPatientAge(),
                exam.getPatientGender(),
                exam.getPhysicianName(),
                exam.getPhysicianCRM(),
                exam.getProcedureName(),
                healthCareInstitutionDomain
        );

        if (!validator.validate(examDomain)){
            return "Invalid exam";
        }

        if (repository.save(examDomain)) {
            return "Saved successfully";
        }

        return "Unable to input exam in database";
    }


}
