package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.RequestStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("requestStatusRepository")
public interface RequestStatusRepository extends JpaRepository<RequestStatusEntity, Integer> {
    RequestStatusEntity findByName(String name);
}