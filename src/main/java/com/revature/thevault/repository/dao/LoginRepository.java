package com.revature.thevault.repository.dao;

import com.revature.thevault.presentation.model.request.ResetPasswordRequest;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.repository.entity.NewLoginCredentialsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<LoginCredentialEntity, Integer> {
    LoginCredentialEntity findByUsername(String username);

    ResetPasswordRequest resetPassword(String username, String email);

    <S extends LoginCredentialEntity> S save(S entity);

    @Override
    Optional<LoginCredentialEntity> findById(Integer integer);

    @Query("select l from LoginCredentialEntity l where l.username= ?1 and l.password = ?2")
    LoginCredentialEntity findByLoginCredential(String username, String password);

    LoginCredentialEntity findByUsernameAndPassword(String username, String password);



//    @Query("select l.userid from LoginCredentialEntity l where l.username = ?1")
//    int findIdByUsername(String memberUsername);
}
