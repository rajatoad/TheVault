package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountProfileEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountProfileRepository")
public interface AccountProfileRepository extends JpaRepository<AccountProfileEntity, Integer> {

    AccountProfileEntity findByLogincredential(LoginCredentialEntity loginCredentialEntity);
}
