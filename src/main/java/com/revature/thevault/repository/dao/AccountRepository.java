package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
