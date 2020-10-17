package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import org.springframework.stereotype.Repository;

@Repository
public final class HealthCareInstitutionRepository {
    private final DataStore dataStore;

    public HealthCareInstitutionRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void save(HealthCareInstitutionDomain institution) {
        //TODO: Handle database errors (Unique Id...)
        dataStore.saveInstitution(institution);
    }

}