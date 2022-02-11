package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.repository.dao.AccountProfileRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountProfileServiceTest {

    @Autowired
    private AccountProfileService accountProfileService;

    @MockBean
    private AccountProfileRepository accountProfileRepository;

    private AccountProfileEntity normalAccountProfileEntity;

    private LoginCredentialEntity normalLoginCredentialEntity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        normalLoginCredentialEntity = new LoginCredentialEntity(
                1,
                "username",
                "password"
        );
        normalAccountProfileEntity = new AccountProfileEntity(
                1,
                normalLoginCredentialEntity,
                "Bob",
                "Tom",
                "some@email.com",
                1238765555L,
                "1 court"
        );
        Mockito.when(accountProfileRepository.findByLogincredential(normalLoginCredentialEntity))
                .thenReturn(normalAccountProfileEntity);
    }

    @Test
    void getProfile() {
        AccountProfileResponse successfulResponse = new AccountProfileResponse(true, normalAccountProfileEntity);
        AccountProfileRequest goodAccountProfileRequest = new AccountProfileRequest(
                normalAccountProfileEntity.getLogincredential().getPk_user_id()
        );
        assertEquals(successfulResponse, accountProfileService.getProfile(goodAccountProfileRequest));
    }

    @Test
    void createProfile() {
    }
}