package com.revature.thevault.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.revature.thevault.repository.dao.DepositTypeRepository;
import com.revature.thevault.repository.entity.DepositTypeEntity;
import com.revature.thevault.service.exceptions.InvalidDepositTypeException;
import com.revature.thevault.service.interfaces.DepositTypeInterface;
@Service("depositTypeService")
public class DepositTypeService implements DepositTypeInterface {
	 @Autowired
	    private DepositTypeRepository depositTypeRepository;

	 
		@Override
		public DepositTypeEntity findDepositTypeEntityByName(String name) {
			
			 DepositTypeEntity depositTypeEntity = depositTypeRepository.findByName(name);
		        if(depositTypeEntity != null) return depositTypeEntity;
		        else throw new InvalidDepositTypeException(HttpStatus.BAD_REQUEST, "Invalid Deposit Type Provided: " + name);
		    }

}
