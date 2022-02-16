package com.revature.thevault.service.interfaces;

import com.revature.thevault.repository.entity.DepositTypeEntity;

public interface DepositTypeInterface {
	 DepositTypeEntity findDepositTypeEntityByName(String name);
}
