package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.repository.entity.NewLoginCredentialsRequest;
import com.revature.thevault.service.dto.LoginCredentialResponse;
import com.revature.thevault.service.exceptions.InvalidInputException;
import com.revature.thevault.service.interfaces.LoginServiceInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("loginService")
public class LoginService implements LoginServiceInterface {

    @Autowired
    private LoginRepository loginRepository;

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
    public GetResponse getLoginCredentialFromLogin(LoginRequest loginRequest) {
        try{
            return GetResponse.builder()
                    .success(true)
                    .gotObject((List) loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()))
                    .build();
        }catch(Exception e){
            throw new InvalidInputException("User was not found");

        }
    }


    @Override
    public PostResponse createNewLogin(LoginCredentialEntity newLoginRequest) {
        try{
            return PostResponse.builder()
                    .success(true)
                    .createdObject(
                            Collections.singletonList(convertEntityToResponse(
                                    loginRepository.save(newLoginRequest))))
                    .build();
        }catch(Exception e){
            throw new InvalidInputException("Please check the information");
        }
    }

    private LoginCredentialResponse convertEntityToResponse(LoginCredentialEntity entity) {
        return new LoginCredentialResponse(entity.getUsername(), entity.getPassword()) ;
    }

    @Override
    public LoginResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

    @Override
    public LoginCredentialEntity findUserByUserId(int userId) {
        return loginRepository.findById(userId).orElse(null);
    }



}
