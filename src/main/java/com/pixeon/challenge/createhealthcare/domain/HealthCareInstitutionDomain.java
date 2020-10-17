package com.pixeon.challenge.createhealthcare.domain;

public final class HealthCareInstitutionDomain {
    private final String name, cnpj;
    private final int coins;

    public HealthCareInstitutionDomain(String name, String cnpj, int coins) {
        this.name = name;
        this.cnpj = cnpj;
        this.coins = coins;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getCoins() {
        return coins;
    }


}
