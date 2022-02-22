package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.AccountTypeRepository;
import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.service.exceptions.InvalidAccountTypeException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.checkerframework.checker.units.qual.A;
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
class AccountTypeServiceTest {

    @Autowired
    private AccountTypeService accountTypeService;

    @MockBean
    private AccountTypeRepository accountTypeRepository;

    private AccountTypeEntity accountTypeEntity;

    @BeforeAll
    void beforeAllSetup(){
        MockitoAnnotations.openMocks(this);
        accountTypeEntity = new AccountTypeEntity(
                1,
                "Checking"
        );
    }

    @BeforeEach
    void beforeEachSetup(){
        Mockito.when(accountTypeRepository.findByName("Checking")).thenReturn(accountTypeEntity);
    }

    @Test
    void findAccountTypeEntityByName() {
        assertEquals(accountTypeEntity, accountTypeService.findAccountTypeEntityByName("Checking"));
    }

    @Test
    void findAccountTypeEntityByNameInvalidName(){
        Mockito.when(accountTypeRepository.findByName("Invalid")).thenReturn(null);
        assertThrows(InvalidAccountTypeException.class, () -> accountTypeService.findAccountTypeEntityByName("Invalid"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "asdfasdfasdfasdfasdfasdfasdfasdf"})
    void findByAccountTypeEntityInvalidInputs(String inputs){
        assertThrows(InvalidRequestException.class, () -> accountTypeService.findAccountTypeEntityByName(inputs));
    }
}