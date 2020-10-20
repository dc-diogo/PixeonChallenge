package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import org.springframework.web.bind.annotation.*;

@RestController
public final class CreateHealthCareInstitutionService {

    private final HealthCareInstitutionRepository repository;
    private final HealthCareInstitutionValidator validator;

    //APAGAR
    private final DataStore dataStore;

    public CreateHealthCareInstitutionService(HealthCareInstitutionRepository repository, HealthCareInstitutionValidator validator, DataStore dataStore) {
        this.repository = repository;
        this.validator = validator;
        this.dataStore = dataStore;
    }

    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        return dataStore.getByCNPJ(name).getCnpj();
    }

    @PostMapping("hc/create")
    public String create( @RequestBody() HealthCareInstitution institution) {
        final HealthCareInstitutionDomain domainInstitution = new HealthCareInstitutionDomain(institution.getName(), institution.getCnpj(), 20);

        if (validator.validate(domainInstitution)){
            repository.save(domainInstitution);
            return "Salvou a clinica";
        }

        return "Deu errado";

    }
}