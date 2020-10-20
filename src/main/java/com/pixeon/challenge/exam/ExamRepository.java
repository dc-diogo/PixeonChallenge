package com.pixeon.challenge.exam;

import com.pixeon.challenge.datastore.DataStore;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private final DataStore dataStore;

    public ExamRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public boolean save(ExamDomain examDomain){
        dataStore.saveExam(examDomain);
        dataStore.discountPixeonCoin(examDomain.getHealthCareInstitutionDomain());

        return true;
    }
}
