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

    @Override
    public GetResponse getProfile(AccountProfileRequest accountProfileRequest) {
        try {
            return GetResponse.builder()
                    .success(true)
                    .gotObject(Collections.singletonList(convertEntityToResponse(accountProfileRepository.getById(accountProfileRequest.getProfileId()))))
                    .build();
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }

    @Override
    public PostResponse createProfile(ProfileCreateRequest profileCreateRequest) {

        try {
            return PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(convertEntityToResponse(accountProfileRepository.save(new AccountProfileEntity(
                            0,
                            new LoginCredentialEntity(profileCreateRequest.getUserId(), "", ""),
                            profileCreateRequest.getFirstName(),
                            profileCreateRequest.getLastName(),
                            profileCreateRequest.getEmail(),
                            profileCreateRequest.getPhoneNumber(),
                            profileCreateRequest.getAddress()
                    )))))
                    .build();
        } catch (Exception e) {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "invalid request");
        }
    }

    @Override
    public PostResponse updateProfile(ProfileCreateRequest profileCreateRequest) {
        try {
            return PostResponse.builder()
                    .success(true)
                    .createdObject(Collections.singletonList(convertEntityToResponse(accountProfileRepository.save(new AccountProfileEntity(
                            0,
                            new LoginCredentialEntity(profileCreateRequest.getUserId(), "", ""),
                            profileCreateRequest.getFirstName(),
                            profileCreateRequest.getLastName(),
                            profileCreateRequest.getEmail(),
                            profileCreateRequest.getPhoneNumber(),
                            profileCreateRequest.getAddress()
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
            accountProfileRepository.delete(optionalProfile.get());
            return DeleteResponse.builder()
                .success(true)
                .deletedObject(Collections.singletonList(convertEntityToResponse(optionalProfile.get())))
                .build();

        }catch(IllegalArgumentException | NullPointerException e){
            throw new InvalidProfileIdException(HttpStatus.BAD_REQUEST, "account profile not found");
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
