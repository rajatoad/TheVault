package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.testng.Assert.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    }

    @Test
    void checkValidLoginTest(){
        assertEquals(validLoginResponse, loginService.checkLogin(validLoginRequest));
    }

    @Test
    void getLoginCredentialSuccessTest() { assertEquals(loginCredentialEntity, loginService.getLoginCredentialFromLogin(validLoginRequest)); }

//    @Test
//    void newAccountTest(){ assertEquals(newLoginCredential, loginService.newAccount(newLoginCredentialsRequest));}

}
