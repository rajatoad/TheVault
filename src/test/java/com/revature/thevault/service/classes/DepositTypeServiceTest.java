package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.DepositRepository;
import com.revature.thevault.repository.dao.DepositTypeRepository;
import com.revature.thevault.repository.entity.DepositTypeEntity;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepositTypeServiceTest {

    @Autowired
    private DepositTypeService depositTypeService;

    @MockBean
    private DepositTypeRepository depositTypeRepository;

    private DepositTypeEntity depositTypeEntity;

    @BeforeAll
    void beforeAllSetup(){
        MockitoAnnotations.openMocks(this);
        depositTypeEntity = new DepositTypeEntity(
                1,
                "Cash"
        );
    }

    @BeforeEach
    void beforeEachSetup(){
        Mockito.when(depositTypeRepository.findByName("Cash")).thenReturn(depositTypeEntity);
    }

    @Test
    void findDepositTypeEntityByName() {
        assertEquals(depositTypeEntity, depositTypeService.findDepositTypeEntityByName("Cash"));
    }

    @Test
    void findDepositTypeEntityByInvalidName(){
        Mockito.when(depositTypeRepository.findByName("Invalid")).thenReturn(null);
        assertThrows(InvalidRequestException.class, () -> depositTypeService.findDepositTypeEntityByName("Invalid"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf"})
    void findDepositTypeByNameInvalidInputs(String input){
        assertThrows(InvalidRequestException.class, () -> depositTypeService.findDepositTypeEntityByName(input));
    }
}