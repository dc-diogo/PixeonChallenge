package com.pixeon.challenge.exam;

import com.pixeon.challenge.createhealthcare.HealthCareInstitution;
import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Component;

@Component
public class ExamValidator {

    public boolean validate(ExamDomain examDomain){

        return validateHealthCareInstitutionCoins(examDomain.getHealthCareInstitutionDomain());
    }

    private boolean validateHealthCareInstitutionCoins(HealthCareInstitutionDomain healthCareInstitutionDomain){

        return healthCareInstitutionDomain.getCoins() > 0;

    }

    private boolean validatePatientName(String patientName){
        return true;
    }

}
