package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.AccountProfileRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.utility.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

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
                "123-876-5555",
                "1 court"
        );

        Mockito.when(accountProfileRepository.findByLogincredential(normalLoginCredentialEntity))
                .thenReturn(normalAccountProfileEntity);
        Mockito.when(accountProfileRepository.deleteByLogincredential(normalLoginCredentialEntity))
                .thenReturn(normalAccountProfileEntity);
    }

    @Test
    void getProfile() {
        AccountProfileRequest goodRequest = new AccountProfileRequest(
                normalAccountProfileEntity.getLogincredential().getPk_user_id()
        );
        GetResponse successfulResponse = GetResponse.builder()
                .success(true)
                .gotObject(Collections.singletonList(accountProfileRepository.getById(goodRequest.getProfileId())))
                .build();

        assertEquals(successfulResponse, accountProfileService.getProfile(goodRequest));
    }

    @Test
    void createProfile() {
        ProfileCreateRequest normalCreateRequest = new ProfileCreateRequest(
                5,
                "joe",
                "something",
                "asdf@fjfo.com",
                "555-555-5555",
                "1 street"
        );

        PostResponse postResponse = PostResponse.builder()
                .success(true)
                .createdObject(Collections.singletonList(accountProfileRepository.save(new AccountProfileEntity(
                        0,
                        new LoginCredentialEntity(normalCreateRequest.getUserId(), "", ""),
                        normalCreateRequest.getFirstName(),
                        normalCreateRequest.getLastName(),
                        normalCreateRequest.getEmail(),
                        normalCreateRequest.getPhoneNumber(),
                        normalCreateRequest.getAddress()
                ))))
                .build();
        assertEquals(postResponse, accountProfileService.createProfile(normalCreateRequest));
    }

    @Test
    void deleteProfile(){
        AccountProfileRequest goodRequest = new AccountProfileRequest(
                normalAccountProfileEntity.getLogincredential().getPk_user_id()
        );
    }
}