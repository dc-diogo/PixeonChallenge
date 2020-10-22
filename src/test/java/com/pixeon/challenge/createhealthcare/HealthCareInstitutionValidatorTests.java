package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthCareInstitutionValidatorTests {

    @Test
    public void Should_NotValidateHealthCareInstitution_When_HealthCareInstitutionNameIsNull(){
        HealthCareInstitutionValidator hcInstitutionValidator = new HealthCareInstitutionValidator();
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain(null, "00000", 20);
        boolean isValidHcInstitution = hcInstitutionValidator.validate(hcInstitutionDomain);
        assertFalse(isValidHcInstitution);
    }

    @Test
    public void Should_NotValidateHealthCareInstitution_When_HealthCareInstitutionNameIsEmpty(){
        HealthCareInstitutionValidator hcInstitutionValidator = new HealthCareInstitutionValidator();
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain("", "00000", 20);
        boolean isValidHcInstitution = hcInstitutionValidator.validate(hcInstitutionDomain);
        assertFalse(isValidHcInstitution);
    }

    @Test
    public void Should_NotValidateHealthCareInstitution_When_HealthCareInstitutionCNPJHasLessThenFourteenCharacters(){
        HealthCareInstitutionValidator hcInstitutionValidator = new HealthCareInstitutionValidator();
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain("Hospital Beta", "00000", 20);
        boolean isValidHcInstitution = hcInstitutionValidator.validate(hcInstitutionDomain);
        assertFalse(isValidHcInstitution);
    }

    @Test
    public void Should_ValidateHealthCareInstitution_When_HealthCareInstitutionHasNameAndCNPJAndCoins(){
        HealthCareInstitutionValidator hcInstitutionValidator = new HealthCareInstitutionValidator();
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain("Hospital Beta", "00000.0000.00000.0000/0000-0", 20);
        boolean isValidHcInstitution = hcInstitutionValidator.validate(hcInstitutionDomain);
        assertTrue(isValidHcInstitution);
    }

}