package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.LoginRequest;
import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.presentation.model.response.LoginResponse;
import com.revature.thevault.repository.dao.LoginRepository;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.repository.entity.NewLoginCredentialsRequest;
import com.revature.thevault.service.exceptions.InvalidInputException;
import com.revature.thevault.service.interfaces.LoginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public LoginCredentialEntity getLoginCredentialFromLogin(LoginRequest loginRequest) {
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

    @Override
    public LoginResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

    @Override
    public LoginCredentialEntity findUserByUserId(int userId) {
        return loginRepository.findById(userId).orElse(null);
    }


}
