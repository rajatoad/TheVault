package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("profileRepository")
public interface ProfileRepository extends JpaRepository<UserProfile,Integer> {


}
