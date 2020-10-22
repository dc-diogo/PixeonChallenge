package com.pixeon.challenge.datastore;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;

public interface DataStore {
    void saveInstitution(HealthCareInstitutionDomain institution);

    HealthCareInstitutionDomain getByCNPJ(String cnpj);

    void saveExam(ExamDomain examDomain);

    int getExamNextIdentifier();

    ExamDomain getExamById(int identifier);

    void updateSearchedExam(ExamDomain examDomain);

    void deleteExam(ExamDomain examDomain);

    int getHealthCareDomainPositionToUpdateCoins(HealthCareInstitutionDomain healthCareInstitutionDomain);

    void updateHealthCareInstitutionCoins(int healthCareIndex, HealthCareInstitutionDomain healthCareDomainUpdatedCoins);

    void updateExam(int identifier, ExamDomain examDomainUpdated);
}
