package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.request.UpdateProfileRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.presentation.model.response.builder.PutResponse;
import com.revature.thevault.repository.entity.LoginCredentialEntity;

public interface AccountProfileInterface {
    GetResponse getProfile(AccountProfileRequest accountProfileRequest);

    PostResponse createProfile(ProfileCreateRequest profileCreateRequest);

    PutResponse updateProfile(UpdateProfileRequest updateProfileRequest);

    DeleteResponse deleteProfile(AccountProfileRequest accountProfileRequest);
}
