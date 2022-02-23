package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.RequestTypeRepository;
import com.revature.thevault.repository.entity.RequestTypeEntity;
import com.revature.thevault.service.exceptions.InvalidRequestException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

class RequestTypeServiceTest {

    @Autowired
    private RequestTypeService requestTypeService;

    @MockBean
    private RequestTypeRepository requestTypeRepository;

    private RequestTypeEntity requestTypeEntity;

    @BeforeAll
    void beforeAllSetup(){
        MockitoAnnotations.openMocks(this);
        requestTypeEntity = new RequestTypeEntity(
                1,
                "Retail"
        );
    }

    @BeforeEach
    void beforeEachSetup(){
        Mockito.when(requestTypeRepository.findByName("Retail")).thenReturn(requestTypeEntity);
    }

    @Test
    void getRequestTypeByName() {
        assertEquals(requestTypeEntity, requestTypeService.getRequestTypeByName("Retail"));
    }

    @Test
    void getRequestTypeInvalidName(){
        Mockito.when(requestTypeRepository.findByName("Invalid")).thenReturn(null);
        assertThrows(InvalidRequestException.class, () -> requestTypeService.getRequestTypeByName("Invalid"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "asdfasdfasdfasdfasdfasdfasdfasdf"})
    void getRequestTypeInvalidInput(String input){
        assertThrows(InvalidRequestException.class, () -> requestTypeService.getRequestTypeByName(input));
    }
}