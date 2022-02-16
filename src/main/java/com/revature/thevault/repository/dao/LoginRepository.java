package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.LoginCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<LoginCredentialEntity, Integer> {
    LoginCredentialEntity findByUsername(String username);

    <S extends LoginCredentialEntity> S save(S entity);

    @Query("select l from LoginCredentialEntity l where l.username= ?1 and l.password = ?2")
    LoginCredentialEntity findByUsernameAndPassword(String username, String password);
}
