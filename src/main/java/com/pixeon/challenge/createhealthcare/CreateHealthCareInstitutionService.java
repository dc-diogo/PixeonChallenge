package com.pixeon.challenge.createhealthcare;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public final class CreateHealthCareInstitutionService {

    private final HealthCareInstitutionRepository repository;
    private final HealthCareInstitutionValidator validator;

    public CreateHealthCareInstitutionService(HealthCareInstitutionRepository repository, HealthCareInstitutionValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @PostMapping("hc/create")
    public ResponseEntity<String> create(@RequestBody() HealthCareInstitution institution) {
        final HealthCareInstitutionDomain domainInstitution = new HealthCareInstitutionDomain(institution.getName(), institution.getCnpj(), 20);

        if (validator.validate(domainInstitution)){
            repository.save(domainInstitution);
            return new ResponseEntity<>("Institution saved successfully.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid institution, please check and try again", HttpStatus.BAD_REQUEST);

    }
}