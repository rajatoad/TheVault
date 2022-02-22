package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.AccountProfileRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.InvalidProfileIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountProfileServiceTest {

    @Autowired
    private AccountProfileService accountProfileService;

    @MockBean
    private AccountProfileRepository accountProfileRepository;

    private AccountProfileEntity normalAccountProfileEntity;

    private LoginCredentialEntity normalLoginCredentialEntity;

    private AccountProfileResponse goodAccountResponse;

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
        goodAccountResponse = new AccountProfileResponse(
                normalAccountProfileEntity.getPk_profile_id(),
                normalAccountProfileEntity.getLogincredential().getPk_user_id(),
                normalAccountProfileEntity.getFirst_name(),
                normalAccountProfileEntity.getLast_name(),
                normalAccountProfileEntity.getEmail(),
                normalAccountProfileEntity.getPhone_number(),
                normalAccountProfileEntity.getAddress()
        );

        Mockito.when(accountProfileRepository.findByLogincredential(normalLoginCredentialEntity))
                .thenReturn(normalAccountProfileEntity);
        Mockito.when(accountProfileRepository.deleteByLogincredential(normalLoginCredentialEntity))
                .thenReturn(normalAccountProfileEntity);
    }

    private AccountProfileResponse convertToProfileResponseEntity(AccountProfileEntity entity){
        return new AccountProfileResponse(
                entity.getPk_profile_id(),
                entity.getLogincredential().getPk_user_id(),
                entity.getFirst_name(),
                entity.getLast_name(),
                entity.getEmail(),
                entity.getPhone_number(),
                entity.getAddress()
        );
    }

    @Test
    void getProfile() {
        AccountProfileRequest apr = new AccountProfileRequest(
                1
        );

        LoginCredentialEntity lce = new LoginCredentialEntity(
                apr.getProfileId(),
                "",
                ""
        );

        Mockito.when(accountProfileRepository.findByLogincredential(lce))
                .thenReturn(normalAccountProfileEntity);

        AccountProfileEntity ape = accountProfileRepository.findByLogincredential(lce);

        GetResponse goodResponse = GetResponse.builder()
                .success(true)
                .gotObject(Collections.singletonList(convertToProfileResponseEntity(ape)))
                .build();

        assertEquals(goodResponse, accountProfileService.getProfile(apr));
    }

    @Test
    void getProfileInvalidRequestException(){
        AccountProfileRequest apr = new AccountProfileRequest(-1);
        LoginCredentialEntity invalidCredential = new LoginCredentialEntity(-1, "", "");

        Mockito.when(accountProfileRepository.findByLogincredential(invalidCredential))
                .thenReturn(null);
        assertThrows(InvalidRequestException.class, () -> accountProfileService.getProfile(apr));

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
        AccountProfileEntity storedProfileEntity = new AccountProfileEntity(
            1,
                new LoginCredentialEntity(normalCreateRequest.getUserId(), "username", "password"),
                normalCreateRequest.getFirstName(),
                normalCreateRequest.getLastName(),
                normalCreateRequest.getEmail(),
                normalCreateRequest.getPhoneNumber(),
                normalCreateRequest.getAddress()

        );
        Mockito.when(accountProfileRepository.save(new AccountProfileEntity(
                0,
                new LoginCredentialEntity(normalCreateRequest.getUserId(), "", ""),
                normalCreateRequest.getFirstName(),
                normalCreateRequest.getLastName(),
                normalCreateRequest.getEmail(),
                normalCreateRequest.getPhoneNumber(),
                normalCreateRequest.getAddress()
        ))).thenReturn(storedProfileEntity);


        PostResponse postResponse = PostResponse.builder()
                .success(true)
                .createdObject(Collections.singletonList(new AccountProfileResponse(
                        storedProfileEntity.getPk_profile_id(),
                        storedProfileEntity.getLogincredential().getPk_user_id(),
                        storedProfileEntity.getFirst_name(),
                        storedProfileEntity.getLast_name(),
                        storedProfileEntity.getEmail(),
                        storedProfileEntity.getPhone_number(),
                        storedProfileEntity.getAddress()
                )))
                .build();

        assertEquals(postResponse, accountProfileService.createProfile(normalCreateRequest));
    }

//    @Test
//    void updateProfile(){
//       ProfileCreateRequest pcr = new ProfileCreateRequest(
//               normalAccountProfileEntity.getPk_profile_id(),
//               normalAccountProfileEntity.getFirst_name(),
//               normalAccountProfileEntity.getLast_name(),
//               normalAccountProfileEntity.getEmail(),
//               normalAccountProfileEntity.getPhone_number(),
//               normalAccountProfileEntity.getAddress()
//       );
//
//       Mockito.when(accountProfileRepository.save(normalAccountProfileEntity))
//               .thenReturn(normalAccountProfileEntity);
//
//       AccountProfileEntity ape = accountProfileRepository.save(normalAccountProfileEntity);
//
//       AccountProfileResponse apr = convertToProfileResponseEntity(ape);
//
//       PostResponse postResponse = PostResponse.builder()
//               .success(true)
//               .createdObject(Collections.singletonList(apr))
//               .build();
//
//       assertEquals(postResponse, accountProfileService.updateProfile(pcr));
//    }

    @Test
    void deleteProfile(){
        AccountProfileRequest goodRequest = new AccountProfileRequest(
                normalAccountProfileEntity.getLogincredential().getPk_user_id()
        );

        Optional<AccountProfileEntity> optionalProfile = Optional.ofNullable(normalAccountProfileEntity);

        Mockito.when(accountProfileRepository.findById(goodRequest.getProfileId()))
                .thenReturn(optionalProfile);

        AccountProfileResponse goodAccountResponse = new AccountProfileResponse(
                normalAccountProfileEntity.getPk_profile_id(),
                normalAccountProfileEntity.getLogincredential().getPk_user_id(),
                normalAccountProfileEntity.getFirst_name(),
                normalAccountProfileEntity.getLast_name(),
                normalAccountProfileEntity.getEmail(),
                normalAccountProfileEntity.getPhone_number(),
                normalAccountProfileEntity.getAddress()
        );

        DeleteResponse goodResponse = DeleteResponse.builder()
                .deletedObject(Collections.singletonList(goodAccountResponse))
                .build();

        Mockito.doNothing().when(accountProfileRepository).delete(optionalProfile.get());
        assertEquals(
                goodResponse,
                accountProfileService.deleteProfile(goodRequest)
        );

    }

    @Test
    void deleteProfileInvalidArgumentException(){
        Mockito.when(accountProfileRepository.findById(-1))
                        .thenReturn(Optional.empty());
        assertThrows(InvalidProfileIdException.class, () -> accountProfileService.deleteProfile(new AccountProfileRequest(-1)));
    }
}