package com.revature.thevault.service.interfaces;

import com.revature.thevault.repository.dao.presentation.model.response.ProfileResponse;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.repository.entity.UserProfile;
import com.revature.thevault.service.classes.ProfileService;

public interface ProfileServiceInterface {

    ProfileResponse newUserProfile(LoginCredentialEntity loginCredentialEntity, String email);

    ProfileResponse convertUserProfileToProfileResponse(UserProfile userProfile);
}
