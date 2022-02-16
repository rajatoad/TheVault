package com.revature.thevault.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.thevault.repository.entity.AccountTypeEntity;
import com.revature.thevault.repository.entity.DepositTypeEntity;

@Repository("accountTypeRepository")
public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity, Integer> {
    AccountTypeEntity findByName(String accountType);
}