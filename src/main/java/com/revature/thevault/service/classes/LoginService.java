package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.presentation.model.request.NewLoginCredentialsRequest;
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
    public LoginCredentialEntity getUserCredentialFromLogin(LoginRequest loginRequest) {
        try{
            return loginRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        }catch(Exception e){
            throw new InvalidInputException("User was not found");

        }
    }

    @Override
    public LoginCredentialEntity newAccount(NewLoginCredentialsRequest newUserAccountRequest) throws InvalidInputException {
        return loginRepository.save(new LoginCredentialEntity(
                0,
                newUserAccountRequest.getUsername(),
                newUserAccountRequest.getPassword()
        ));
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
