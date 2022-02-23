package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.RequestStatusRepository;
import com.revature.thevault.repository.entity.RequestStatusEntity;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RequestStatusServiceTest {

    @Autowired
    private RequestStatusService requestStatusService;

    @MockBean
    private RequestStatusRepository requestStatusRepository;

    private RequestStatusEntity requestStatusEntity;

    @BeforeAll
    void beforeAllSetup(){
        MockitoAnnotations.openMocks(this);
        requestStatusEntity = new RequestStatusEntity(
          1,
          "Pending"
        );
    }

    @BeforeEach
    void beforeEachSetup(){
        Mockito.when(requestStatusRepository.findByName("Pending")).thenReturn(requestStatusEntity);
    }

    @Test
    void getRequestStatusByName() {
        assertEquals(requestStatusEntity, requestStatusService.getRequestStatusByName("Pending"));
    }

    @Test
    void getRequestStatusByInvalidName(){
        Mockito.when(requestStatusRepository.findByName("Invalid")).thenReturn(null);
        assertThrows(InvalidRequestException.class, () -> requestStatusService.getRequestStatusByName("Invalid"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "asdfasdfasdfasdfasdfasdfasdfasdf"})
    void getRequestStatusByInvalidInputs(String inputs){
        assertThrows(InvalidRequestException.class, () -> requestStatusService.getRequestStatusByName(inputs));
    }
}