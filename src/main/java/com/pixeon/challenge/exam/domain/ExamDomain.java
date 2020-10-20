package com.pixeon.challenge.exam.domain;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;

public class ExamDomain {

    private final int identifier;
    private final String patientName;
    private final int patientAge;
    private final char patientGender;
    private final String physicianName;
    private final String physicianCRM;
    private final String procedureName;
    private final HealthCareInstitutionDomain healthCareInstitutionDomain;
    private final boolean examAlreadySearched;


    public ExamDomain(int identifier, String patientName, int patientAge, char patientGender, String physicianName, String physicianCRM, String procedureName, HealthCareInstitutionDomain healthCareInstitutionDomain, boolean examAlreadySearched) {
        this.identifier = identifier;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.physicianName = physicianName;
        this.physicianCRM = physicianCRM;
        this.procedureName = procedureName;
        this.healthCareInstitutionDomain = healthCareInstitutionDomain;
        this.examAlreadySearched = examAlreadySearched;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public char getPatientGender() {
        return patientGender;
    }

    public String getPhysicianName() {
        return physicianName;
    }

    public String getPhysicianCRM() {
        return physicianCRM;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public HealthCareInstitutionDomain getHealthCareInstitutionDomain() {
        return healthCareInstitutionDomain;
    }

    public boolean isExamAlreadySearched() {
        return examAlreadySearched;
    }
}
