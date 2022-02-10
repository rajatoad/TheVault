package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.ProfileRepository;
import com.revature.thevault.repository.dao.presentation.model.response.ProfileResponse;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.repository.entity.UserProfile;
import com.revature.thevault.service.interfaces.ProfileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;


public class ProfileService implements ProfileServiceInterface {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileResponse newUserProfile(LoginCredentialEntity loginCredentialEntity, String email) {
        return convertUserProfileToProfileResponse(profileRepository.save(new UserProfile(0, loginCredentialEntity, "", "", email)));
    }

    @Override
    public ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile) {
        return null;
    }
}
