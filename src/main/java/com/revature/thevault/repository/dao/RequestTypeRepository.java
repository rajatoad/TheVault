package com.revature.thevault.repository.dao;

import com.revature.thevault.repository.entity.RequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("requestTypeRepository")
public interface RequestTypeRepository extends JpaRepository<RequestTypeEntity, Integer> {
    RequestTypeEntity findByName(String name);
}
