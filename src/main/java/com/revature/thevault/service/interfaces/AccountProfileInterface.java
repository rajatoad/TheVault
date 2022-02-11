package com.revature.thevault.service.interfaces;

import com.revature.thevault.presentation.model.request.AccountProfileRequest;
import com.revature.thevault.presentation.model.request.ProfileCreateRequest;
import com.revature.thevault.presentation.model.response.AccountProfileResponse;

public interface AccountProfileInterface {
    AccountProfileResponse getProfile(AccountProfileRequest accountProfileRequest);

    AccountProfileResponse createProfile(ProfileCreateRequest profileCreateRequest);
}
