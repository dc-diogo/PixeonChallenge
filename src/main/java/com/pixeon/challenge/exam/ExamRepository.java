package com.pixeon.challenge.exam;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private final DataStore dataStore;

    public ExamRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public String saveExam(ExamDomain examDomain) {
        if (hasCoins(examDomain.getHealthCareInstitutionDomain())) {
            dataStore.saveExam(examDomain);
            discountPixeonCoin(examDomain);
            return "Exam saved!";
        }
        return "Not enough pixeon coins";
    }

    public Exam findExam(int identifier) {

        ExamDomain examDomain = dataStore.getExamById(identifier);
        if (examDomain == null) {
            return null;
        }
        chargePixeonCoinIfNotRetrieved(examDomain);
        return generateExamReturnObject(examDomain);
    }

    public Exam updateExam(Exam exam) {

        ExamDomain examDomain = dataStore.getExamById(exam.getIdentifier());

        if (examDomain == null){
            return null;
        }

        HealthCareInstitutionDomain hcInstitutionDomain = dataStore.getByCNPJ(exam.getInstitutionCNPJ());

        if (hcInstitutionDomain == null) {
            return exam;
        }

        ExamDomain examDomainUpdated = new ExamDomain(
                examDomain.getIdentifier(),
                exam.getPatientName(),
                exam.getPatientAge(),
                exam.getPatientGender(),
                exam.getPhysicianName(),
                exam.getPhysicianCRM(),
                exam.getProcedureName(),
                hcInstitutionDomain,
                examDomain.isExamAlreadySearched()
        );

        dataStore.updateExam(examDomain.getIdentifier(), examDomainUpdated);

        return generateExamReturnObject(examDomainUpdated);
    }

    public String deleteExam(int identifier, String hcInstitutionCnpj) {
        HealthCareInstitutionDomain healthCareInstitutionDomain = getByCNPJ(hcInstitutionCnpj);
        if (healthCareInstitutionDomain == null)
            return "Institution not registered in Health Care Institutions Database";

        ExamDomain examDomain = dataStore.getExamById(identifier);

        if (examDomain == null)
            return "Exam not found in database";

        if (!examDomain.getHealthCareInstitutionDomain().getCnpj().equals(hcInstitutionCnpj))
            return "You don't have permission to delete this exam. This exam belongs to another Health Care Institution";

        dataStore.deleteExam(examDomain);
        return "Exam deleted successfully";
    }

    public ExamDomain getExamDomain(Exam exam) {
        HealthCareInstitutionDomain healthCareInstitutionDomain = getByCNPJ(exam.getInstitutionCNPJ());

        if (healthCareInstitutionDomain == null) {
            return null;
        }

        ExamDomain examDomain = new ExamDomain(
                getExamNextIdentifier(),
                exam.getPatientName(),
                exam.getPatientAge(),
                exam.getPatientGender(),
                exam.getPhysicianName(),
                exam.getPhysicianCRM(),
                exam.getProcedureName(),
                healthCareInstitutionDomain,
                false
        );

        return examDomain;
    }

    private int getExamNextIdentifier() {
        return dataStore.getExamNextIdentifier();
    }

    private boolean hasCoins(HealthCareInstitutionDomain healthCareInstitutionDomain) {
        return healthCareInstitutionDomain.getCoins() > 0;
    }

    private Exam generateExamReturnObject(ExamDomain examDomain) {
        Exam exam = new Exam(
                examDomain.getIdentifier(),
                examDomain.getPatientName(),
                examDomain.getPatientAge(),
                examDomain.getPatientGender(),
                examDomain.getPhysicianName(),
                examDomain.getPhysicianCRM(),
                examDomain.getProcedureName(),
                examDomain.getHealthCareInstitutionDomain().getCnpj());
        return exam;
    }

    private void chargePixeonCoinIfNotRetrieved(ExamDomain examDomain) {
        if (!examDomain.isExamAlreadySearched()) {
            discountPixeonCoin(examDomain);
            dataStore.updateSearchedExam(examDomain);
        }
    }

    private void discountPixeonCoin(ExamDomain examDomain) {
        int healthCareDomainPositionToUpdateCoins = dataStore.getHealthCareDomainPositionToUpdateCoins(examDomain.getHealthCareInstitutionDomain());
        dataStore.updateHealthCareInstitutionCoins(healthCareDomainPositionToUpdateCoins, examDomain.getHealthCareInstitutionDomain());
    }

    private HealthCareInstitutionDomain getByCNPJ(String institutionCNPJ) {
        return dataStore.getByCNPJ(institutionCNPJ);
    }


}
