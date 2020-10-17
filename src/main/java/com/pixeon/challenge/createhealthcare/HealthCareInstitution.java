package com.pixeon.challenge.createhealthcare;

public final class HealthCareInstitution {

    private final String name;
    private final String cnpj;

    public HealthCareInstitution(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

}
