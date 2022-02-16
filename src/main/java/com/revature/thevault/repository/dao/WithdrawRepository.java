package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.RequestTypeEntity;
import com.revature.thevault.repository.entity.WithdrawEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<WithdrawEntity, Integer> {

    List<WithdrawEntity> findByAccountentity(AccountEntity accountEntity);

    List<WithdrawEntity> findByAccountentityAndRequesttypeentity(AccountEntity accountEntity, RequestTypeEntity requestTypeByName);
}
