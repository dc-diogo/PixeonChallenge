package com.pixeon.challenge.exam;

import com.pixeon.challenge.createhealthcare.HealthCareInstitution;
import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Component;

@Component
public class ExamValidator {

    public boolean validate(ExamDomain examDomain){

        if (validateHealthCareInstitutionCoins(examDomain.getHealthCareInstitutionDomain())){
            return true;
        }
        return false;
    }

    private boolean validateHealthCareInstitutionCoins(HealthCareInstitutionDomain healthCareInstitutionDomain){

        return healthCareInstitutionDomain.getCoins() > 0;

    }

    private boolean validatePatientName(String patientName){
        return true;
    }

    private boolean validadePatientAge(String patientAge){
        return true;
    }

    private boolean validadePatientGender(char gender){
        return true;
    }

    private boolean validadePhysicianName(String physicianName){
        return true;
    }

    private boolean validadePhysicianCRM(String crm){
        return true;
    }

    private boolean validadeProcedureName(String procedure){
        return true;
    }

    private boolean validadeHCInstitution(HealthCareInstitution institution){
        return true;
    }
}
