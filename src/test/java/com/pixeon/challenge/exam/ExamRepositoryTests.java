package com.pixeon.challenge.exam;

import com.pixeon.challenge.createhealthcare.domain.HealthCareInstitutionDomain;
import com.pixeon.challenge.datastore.DataStore;
import com.pixeon.challenge.exam.domain.ExamDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExamRepositoryTests {

    @Test
    public void testFailToSaveWhenNotEnoughCoins(){

        DataStore dataStoreMock = mock(DataStore.class);
        ExamRepository examRepository = new ExamRepository(dataStoreMock);
        HealthCareInstitutionDomain hcInstitutionDomain = new HealthCareInstitutionDomain(
                "Hospital East",
                "00000",
                1
        );
        ExamDomain examDomain = new ExamDomain(
                1,
                "Patient Zero",
                21,
                'm',
                "Nicholas Tesla",
                "CRE-2323",
                "X-ray",
                hcInstitutionDomain,
                false
        );

        String result = examRepository.saveExam(examDomain);

        assertEquals("Exam saved!", result);
        verify(dataStoreMock, times(1)).saveExam(examDomain);

    }

}
