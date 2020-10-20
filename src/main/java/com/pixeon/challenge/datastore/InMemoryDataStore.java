package com.pixeon.challenge.datastore;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public final class InMemoryDataStore implements DataStore {
    private final List<HealthCareInstitutionDomain> institutions = new ArrayList();
    private final List<ExamDomain> exams = new ArrayList<>();

    @Override
    public void saveInstitution(HealthCareInstitutionDomain institution) {
        institutions.add(institution);
    }

    @Override
    public HealthCareInstitutionDomain getByName(String name) {
        // TODO: read more about Optional<>

        Optional<HealthCareInstitutionDomain> found = institutions
                .stream()
                .filter(healthCareInstitutionDomain -> healthCareInstitutionDomain.getName().equals(name))
                .findFirst();
        return found.orElse(null);

    }

    @Override
    public void saveExam(ExamDomain examDomain) {
        exams.add(examDomain);
    }

    @Override
    public int getExamNextIdentifier() {
        return exams.size() + 1;
    }

    @Override
    public void discountPixeonCoin(HealthCareInstitutionDomain healthCareInstitutionDomain) {
        int healthCareIndex = institutions.indexOf(healthCareInstitutionDomain);
        HealthCareInstitutionDomain healthCareDomainUpdatedCoins = new HealthCareInstitutionDomain(
                healthCareInstitutionDomain.getName(),
                healthCareInstitutionDomain.getCnpj(),
                healthCareInstitutionDomain.getCoins() - 1
        );

        institutions.set(healthCareIndex, healthCareDomainUpdatedCoins);
    }

    @Override
    public ExamDomain getExamById(int identifier) {

        Optional<ExamDomain> examRetrieved = exams
                .stream()
                .filter(exam -> exam.getIdentifier() == identifier)
                .findFirst();

        return examRetrieved.orElse(null);
    }
}
