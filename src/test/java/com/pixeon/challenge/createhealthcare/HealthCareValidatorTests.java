package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.HealthCareInstitutionValidator;
import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthCareValidatorTests {

    @Test
    public void testIfNameIsNotNull(){
        HealthCareInstitutionValidator hcInstitutionValidator = new HealthCareInstitutionValidator();
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain(null, "00000", 20);
        boolean isValidHcInstitution = hcInstitutionValidator.validate(hcInstitutionDomain);
        assertFalse(isValidHcInstitution, "Name is null");
    }

}