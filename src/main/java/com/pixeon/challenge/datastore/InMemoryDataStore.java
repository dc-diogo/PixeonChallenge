package com.pixeon.challenge.datastore;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public final class InMemoryDataStore implements DataStore {
    private final List<HealthCareInstitutionDomain> institutions = new ArrayList();
    private final List<ExamDomain> exams = new ArrayList<>();

    @Override
    public void saveInstitution(HealthCareInstitutionDomain institution) {
        institutions.add(institution);
    }

    @Override
    public HealthCareInstitutionDomain getByCNPJ(String cnpj) {
        Optional<HealthCareInstitutionDomain> found = institutions
                .stream()
                .filter(healthCareInstitutionDomain -> healthCareInstitutionDomain.getCnpj().equals(cnpj))
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


    public Optional<HealthCareInstitutionDomain> findHealthCareInstitutionDomainByCnpj(HealthCareInstitutionDomain healthCareInstitutionDomain) {
        Optional<HealthCareInstitutionDomain> hcInstitution = institutions
                .stream()
                .filter(institution -> Objects.equals(institution.getCnpj(), healthCareInstitutionDomain.getCnpj()))
                .findFirst();

        return hcInstitution;
    }

    public int getHealthCareDomainPositionToUpdateCoins(HealthCareInstitutionDomain healthCareInstitutionDomain) {
        Optional<HealthCareInstitutionDomain> hcInstitution = findHealthCareInstitutionDomainByCnpj(healthCareInstitutionDomain);
        return institutions.indexOf(hcInstitution.get());
    }

    public void updateHealthCareInstitutionCoins(int healthCareIndex, HealthCareInstitutionDomain healthCareDomainUpdatedCoins) {
        institutions.set(healthCareIndex, healthCareDomainUpdatedCoins);
    }

    @Override
    public ExamDomain getExamById(int identifier) {
        return findExam(identifier, exams);
    }


    private ExamDomain findExam(int identifier, List<ExamDomain> examsList) {
        Optional<ExamDomain> examRetrieved = examsList
                .stream()
                .filter(exam -> exam.getIdentifier() == identifier)
                .findFirst();
        return examRetrieved.orElse(null);
    }

    @Override
    public void updateSearchedExam(ExamDomain examDomain) {

        int examIndex = exams.indexOf(examDomain);
        ExamDomain examDomainUpdated = new ExamDomain(
                examDomain.getIdentifier(),
                examDomain.getPatientName(),
                examDomain.getPatientAge(),
                examDomain.getPatientGender(),
                examDomain.getPhysicianName(),
                examDomain.getPhysicianCRM(),
                examDomain.getProcedureName(),
                examDomain.getHealthCareInstitutionDomain(),
                true
        );

        exams.set(examIndex, examDomainUpdated);


    }

    @Override
    public void deleteExam(ExamDomain exam) {
        exams.remove(exam);
    }

    @Override
    public void updateExam(int examIndex, ExamDomain examDomainUpdated) {
        exams.set(examIndex, examDomainUpdated);
    }
}
