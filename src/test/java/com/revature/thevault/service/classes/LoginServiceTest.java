package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.dto.LoginResponseObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)

public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private LoginRepository loginRepository;

    private LoginResponse validLoginResponse;
    private LoginRequest validLoginRequest;
    private LoginCredentialEntity loginCredentialEntity;
    private LoginRequest storedUserCred;




    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        int userId = 1;
        String validUsername = "username1";
        String validPassword = "password1";

        validLoginResponse = new LoginResponse(true);
        validLoginRequest = new LoginRequest(validUsername, validPassword);
        loginCredentialEntity = new LoginCredentialEntity(userId, validUsername, validPassword);
        storedUserCred = new LoginRequest(validLoginRequest.getUsername(), validLoginRequest.getPassword());



        Mockito.when(loginRepository.findByUsername(validUsername)).thenReturn(loginCredentialEntity);
        Mockito.when(loginRepository.findByUsernameAndPassword(validLoginRequest.getUsername(), validLoginRequest.getPassword())).thenReturn(loginCredentialEntity);
        Mockito.when(loginRepository.findById(userId)).thenReturn(Optional.ofNullable(loginCredentialEntity));
    }

    private LoginResponseObject convertEntityToResponse(LoginCredentialEntity loginCredentialEntity){
        return new LoginResponseObject(
                loginCredentialEntity.getPk_user_id(),
                loginCredentialEntity.getUsername(),
                loginCredentialEntity.getPassword(),
                "lol"
        );
    }

    @Test
    void checkValidLoginTest(){
        Mockito.when(loginRepository.findByUsername(validLoginRequest.getUsername())).thenReturn(loginCredentialEntity);

        LoginCredentialEntity goodRequest = loginRepository.findByUsername(validLoginRequest.getUsername());

        assertEquals(validLoginResponse, loginService.checkLogin(validLoginRequest));
    }

    @Test
    void resetPassword(){
        ResetPasswordRequest validResetRequest = new ResetPasswordRequest(
                "username1",
                "password1"
        );

        StringBuilder passwordResetter = new StringBuilder("S16oRl1u67eQ3EfT");

        Mockito.when(loginRepository.findByUsername(validResetRequest.getUsername()))
                .thenReturn(loginCredentialEntity);

        LoginCredentialEntity resetEntity = loginRepository.findByUsername(validResetRequest.getUsername());

        resetEntity.setPassword(passwordResetter.toString());

        Mockito.when(loginRepository.save(resetEntity))
                        .thenReturn(resetEntity);

        PutResponse resetResponse = PutResponse.builder()
                .success(true)
                .updatedObject(Collections.singletonList(loginRepository.save(resetEntity)))
                .build();

        assertEquals(resetResponse, loginService.resetPassword(validResetRequest));

    }

    @Test
    void findUserByUserIdTest(){assertEquals(loginService.findUserByUserId(loginCredentialEntity.getPk_user_id()), loginCredentialEntity);}


}
