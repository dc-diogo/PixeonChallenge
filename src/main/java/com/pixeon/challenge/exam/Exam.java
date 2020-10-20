package com.pixeon.challenge.exam;


public class Exam {

    private final String patientName;
    private final int patientAge;
    private final char patientGender;
    private final String physicianName;
    private final String physicianCRM;
    private final String procedureName;
    private final String institutionName;

    public Exam(String patientName, int patientAge, char patientGender, String physicianName, String physicianCRM, String procedureName, String institutionName) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.physicianName = physicianName;
        this.physicianCRM = physicianCRM;
        this.procedureName = procedureName;
        this.institutionName = institutionName;
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

    public String getInstitutionName() {
        return institutionName;
    }
}
