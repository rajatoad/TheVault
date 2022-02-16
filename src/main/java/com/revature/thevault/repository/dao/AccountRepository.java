package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    List<AccountEntity> findByLogincredentials(LoginCredentialEntity loginCredential);
}
