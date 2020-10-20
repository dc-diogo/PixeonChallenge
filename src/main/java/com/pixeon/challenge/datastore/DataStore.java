package com.pixeon.challenge.datastore;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;

public interface DataStore {
    void saveInstitution(HealthCareInstitutionDomain institution);

    HealthCareInstitutionDomain getByName(String name);

    void saveExam(ExamDomain examDomain);

    int getExamNextIdentifier();

    void discountPixeonCoin(HealthCareInstitutionDomain healthCareInstitutionDomain);

    ExamDomain getExamById(int identifier);
}
