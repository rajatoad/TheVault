package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.NewLoginCredentialsRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.dto.LoginResponseObject;
import com.revature.thevault.service.exceptions.InvalidInputException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.interfaces.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("loginService")
public class LoginService implements LoginServiceInterface {

    @Autowired
    private LoginRepository loginRepository;

//    @Autowired
//    private AccountProfileService accountProfileService;

    @Override
    public LoginResponse checkLogin(LoginRequest loginRequest) {
        LoginCredentialEntity loginCredentialEntity = loginRepository.findByUsername(loginRequest.getUsername());
        if(loginCredentialEntity.getPassword().contentEquals(loginRequest.getPassword())) {
            return new LoginResponse(true);
        }else{
            return new LoginResponse(false);
        }
    }

    @Override
    public PostResponse getLoginCredentialFromLogin(LoginRequest loginRequest) {
        try{
            return PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(convertEntityToResponse(loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()))))
                    .build();
        }catch(Exception e){
            throw new InvalidInputException("User was not found");

        }
    }

    @Override
    public PostResponse createNewLogin(NewLoginCredentialsRequest newLoginRequest) {
        try{
            return PostResponse.builder()
                    .success(true)
                    .createdObject(
                            Collections.singletonList(convertEntityToResponse(
                                    loginRepository.save(
                                            new LoginCredentialEntity(
                                                    0,
                                                    newLoginRequest.getUsername(),
                                                    newLoginRequest.getPassword()
                                            )
                                    ))))
                    .build();
        }catch(Exception e){
            throw new InvalidInputException("Please check the information");
        }
    }

    @Override
    public PutResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        String[] randomPhrases = {"S16oRl1u", "67eQ3EfT", "vzDo", "tFECo", "yfQKYW5", "kfPYi", "mxYCQ6Y8", "C4z9s55", "v19n", "qih3", "vJbetBI", "1UYFn", "sxs1daex", "0W1aB5q9", "218qjn83", "rP48L", "8LF4rZ", "Q2N9p", "3wE", "LfIQd", "i3z", "XRP", "Tjpk7R", "E3Q9BnF", "Yb9C"};
        StringBuilder passwordResetter = new StringBuilder("");

        for (int i = 0; i < 2; i++) {
            double doubleRandomNumber = Math.random() * 25;
            int randomNumber = (int) doubleRandomNumber;
            passwordResetter.append(randomPhrases[randomNumber]);
        }
        try{
            LoginCredentialEntity loginCredentialEntity = loginRepository.findByUsername(resetPasswordRequest.getUsername());
//            AccountProfileResponse accountProfileResponse = (AccountProfileResponse) accountProfileService.getProfile(new AccountProfileRequest(loginCredentialEntity.getPk_user_id())).getGotObject().get(0);
            loginCredentialEntity.setPassword(passwordResetter.toString());
//            if(accountProfileResponse.getEmail().contentEquals(resetPasswordRequest.getEmail()))
            return PutResponse.builder()
                    .success(true)
                    .updatedObject(Collections.singletonList(loginRepository.save(loginCredentialEntity)))
                    .build();
//            else throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid Email");
        }catch (NullPointerException e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }

    }

    @Override
    public LoginCredentialEntity findUserByUserId(int userId) {
        return loginRepository.findById(userId).orElse(null);
    }



    public PostResponse validateLogin(LoginRequest loginRequest) {
        try{
            LoginCredentialEntity loginCredentialEntity = loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
            return PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(convertEntityToResponse(loginCredentialEntity)))
                    .build();
        }catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private LoginResponseObject convertEntityToResponse(LoginCredentialEntity loginCredentialEntity) {
        return new LoginResponseObject(
                loginCredentialEntity.getPk_user_id(),
                loginCredentialEntity.getUsername(),
                loginCredentialEntity.getPassword()
        );
    }
}
