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
        dataStore.saveExam(examDomain);
        dataStore.discountPixeonCoin(examDomain.getHealthCareInstitutionDomain());
        return true;
    }


    public Exam getExamById(int identifier) {

        ExamDomain examDomain = dataStore.getExamById(identifier);
        if (examDomain == null){
          return null;
        } else{
            if (!examDomain.isExamAlreadySearched()){
                dataStore.discountPixeonCoin(examDomain.getHealthCareInstitutionDomain());
                dataStore.updateSearchedExam(examDomain);
            }
        }

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


    public HealthCareInstitutionDomain getByCNPJ(String institutionCNPJ) {
        return dataStore.getByCNPJ(institutionCNPJ);
    }

    public int getExamNextIdentifier() {
        return dataStore.getExamNextIdentifier();
    }
}
