package com.revature.thevault.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.thevault.repository.entity.DepositTypeEntity;

@Repository("depositTypeRepository")
public interface DepositTypeRepository extends JpaRepository<DepositTypeEntity, Integer> {
    DepositTypeEntity findByName(String name);
}