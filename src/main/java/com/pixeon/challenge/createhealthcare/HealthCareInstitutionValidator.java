package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import org.springframework.stereotype.Component;

@Component
public class HealthCareInstitutionValidator {

    public boolean validate(HealthCareInstitutionDomain domain){

        return validateName(domain.getName()) && validateCnpj(domain.getCnpj());
    }

    private boolean validateName(String name){

        if (name == null) return false;
        return !name.equals("");
    }

    private boolean validateCnpj(String cnpj){

        return cnpj.length() >= 5;
    }
}
