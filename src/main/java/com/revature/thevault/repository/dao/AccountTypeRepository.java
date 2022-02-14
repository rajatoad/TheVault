package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountTypeRepository")
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Integer> {
    AccountTypeEntity findByName(String accountType);
}
