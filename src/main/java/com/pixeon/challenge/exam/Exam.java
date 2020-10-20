package com.pixeon.challenge.exam;


public class Exam {

    private final int identifier;
    private final String patientName;
    private final int patientAge;
    private final char patientGender;
    private final String physicianName;
    private final String physicianCRM;
    private final String procedureName;
    private final String institutionCNPJ;


    public Exam(int identifier, String patientName, int patientAge, char patientGender, String physicianName, String physicianCRM, String procedureName, String institutionCNPJ) {
        this.identifier = identifier;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.physicianName = physicianName;
        this.physicianCRM = physicianCRM;
        this.procedureName = procedureName;
        this.institutionCNPJ = institutionCNPJ;
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

    public String getInstitutionCNPJ() {
        return institutionCNPJ;
    }

    public int getIdentifier() {
        return identifier;
    }
}
