package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.AccountProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("accountProfileRepository")
public interface AccountProfileRepository extends JpaRepository<AccountProfileEntity, Integer> {

    AccountProfileEntity findByFk_user_id(int userId);
}
