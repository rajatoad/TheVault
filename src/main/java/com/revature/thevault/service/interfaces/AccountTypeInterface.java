package com.revature.thevault.service.interfaces;

import com.revature.thevault.repository.entity.AccountTypeEntity;

public interface AccountTypeInterface {
    AccountTypeEntity findAccountTypeEntityByName(String name);
}
