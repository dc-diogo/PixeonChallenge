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

    public boolean save(ExamDomain examDomain){
        if (hasCoins(examDomain.getHealthCareInstitutionDomain())){
            dataStore.saveExam(examDomain);
            dataStore.discountPixeonCoin(examDomain.getHealthCareInstitutionDomain());
            return true;
        }
        return false;
    }

    private boolean hasCoins(HealthCareInstitutionDomain healthCareInstitutionDomain){
        return healthCareInstitutionDomain.getCoins() > 0 ? true : false;
    }

    public Exam getExamById(int identifier) {

        ExamDomain examDomain = getExamDomainById(identifier);
        if (examDomain == null) return null;

        return generateExamReturnObject(examDomain);
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

    public int getExamNextIdentifier() {
        return dataStore.getExamNextIdentifier();
    }

    public Exam updateExam(Exam exam) {

        ExamDomain examDomain = getExamDomainById(exam.getIdentifier());
        HealthCareInstitutionDomain hcInstitutionDomain = dataStore.getByCNPJ(exam.getInstitutionCNPJ());

        if (hcInstitutionDomain == null){
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
        

        return generateExamReturnObject(examDomainUpdated);
    }

    private ExamDomain getExamDomainById(int identifier) {
        ExamDomain examDomain = dataStore.getExamById(identifier);
        if (examDomain == null){
            return null;
        } else{
            if (!examDomain.isExamAlreadySearched()){
                dataStore.discountPixeonCoin(examDomain.getHealthCareInstitutionDomain());
                dataStore.updateSearchedExam(examDomain);
            }
        }
        return examDomain;
    }

    public HealthCareInstitutionDomain getByCNPJ(String institutionCNPJ) {
        return dataStore.getByCNPJ(institutionCNPJ);
    }

    public ExamDomain getExamDomain(Exam exam, HealthCareInstitutionDomain healthCareInstitutionDomain) {
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
}
