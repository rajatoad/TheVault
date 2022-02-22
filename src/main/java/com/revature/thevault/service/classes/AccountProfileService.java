package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.request.UpdateProfileRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.dao.AccountProfileRepository;
import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.exceptions.InvalidProfileIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.interfaces.AccountProfileInterface;
import com.revature.thevault.utility.enums.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service("accountProfileService")
public class AccountProfileService implements AccountProfileInterface {

    @Autowired
    private AccountProfileRepository accountProfileRepository;

    @Autowired
    private LoginService loginService;

    @Override
    public GetResponse getProfile(AccountProfileRequest accountProfileRequest) {
        LoginCredentialEntity loginCredential = new LoginCredentialEntity(
                accountProfileRequest.getProfileId(),
                "",
                ""
        );

        AccountProfileEntity profile = accountProfileRepository.findByLogincredential(loginCredential);

        try {
            return GetResponse.builder()
                    .success(true)
//                    .gotObject(Collections.singletonList(convertEntityToResponse(accountProfileRepository.getById(accountProfileRequest.getProfileId()))))
                    .gotObject(Collections.singletonList(convertEntityToResponse(profile)))
                    .build();
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }

    @Override
    public PostResponse createProfile(ProfileCreateRequest profileCreateRequest) {
        AccountProfileEntity createdProfileEntity = new AccountProfileEntity(
                0,
                loginService.findUserByUserId(profileCreateRequest.getUserId()),
                profileCreateRequest.getFirstName(),
                profileCreateRequest.getLastName(),
                profileCreateRequest.getEmail(),
                profileCreateRequest.getPhoneNumber(),
                profileCreateRequest.getAddress()
        );

        AccountProfileResponse convertedCreatedEntity = convertEntityToResponse(accountProfileRepository.save(createdProfileEntity));

        try {
            return PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(convertedCreatedEntity))
                    .build();
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }

    @Override
    public PutResponse updateProfile(UpdateProfileRequest updateProfileRequest) {
        try {
            return PutResponse.builder()
                    .success(true)
                    .updatedObject(Collections.singletonList(convertEntityToResponse(accountProfileRepository.save(new AccountProfileEntity(
                            updateProfileRequest.getUserId(),
                            loginService.findUserByUserId(updateProfileRequest.getUserId()),
                            updateProfileRequest.getFirstName(),
                            updateProfileRequest.getLastName(),
                            updateProfileRequest.getEmail(),
                            updateProfileRequest.getPhoneNumber(),
                            updateProfileRequest.getAddress()
                    )))))
                    .build();
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }


    @Override
    @Transactional
    public DeleteResponse deleteProfile(AccountProfileRequest accountProfileRequest) {

        try {
            Optional<AccountProfileEntity> optionalProfile = accountProfileRepository.findById(accountProfileRequest.getProfileId());
            if(optionalProfile.isPresent()) {
                accountProfileRepository.delete(optionalProfile.get());
                return DeleteResponse.builder()
                        .success(true)
                        .deletedObject(Collections.singletonList(convertEntityToResponse(optionalProfile.get())))
                        .build();
            }
            else{
                throw new InvalidProfileIdException(HttpStatus.BAD_REQUEST, "account profile not found");
            }
        }catch(InvalidProfileIdException e){
            throw e;
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }

    private AccountProfileResponse convertEntityToResponse(AccountProfileEntity accountProfileEntity) {
        return new AccountProfileResponse(
                accountProfileEntity.getPk_profile_id(),
                accountProfileEntity.getLogincredential().getPk_user_id(),
                accountProfileEntity.getFirst_name(),
                accountProfileEntity.getLast_name(),
                accountProfileEntity.getEmail(),
                accountProfileEntity.getPhone_number(),
                accountProfileEntity.getAddress()
        );
    }
}
