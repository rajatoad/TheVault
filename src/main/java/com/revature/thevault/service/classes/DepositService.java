package com.revature.thevault.service.classes;


import com.revature.thevault.presentation.model.request.CreateAccountRequest;
import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.DepositRepository;
import com.revature.thevault.repository.entity.AccountEntity;
import com.revature.thevault.repository.entity.DepositEntity;
import com.revature.thevault.repository.entity.LoginCredentialEntity;
import com.revature.thevault.service.dto.DepositResponseObject;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.interfaces.DepositServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("depositService")
public class DepositService implements DepositServiceInterface {
	 @Autowired
	    private DepositRepository depositRepository;
	 @Autowired
	 private DepositTypeService depositTypeService;
	 @Autowired
	 private AccountTypeService accountTypeService;
	@Override
    public PostResponse createDeposit(DepositRequest depositRequest) {
//		  try {
//	            return PostResponse.builder()
//	                    .success(true)
//	                    .createdObject(Collections.singletonList(
//	                            convertDepositEntityToResponse(
//	                                    depositRepository.save( 
//	                                    		0,
//	                                    		0,
		return null;	    }
    @Override
  public GetResponse getAllUserDeposits(int accountId) {
        try {
            List<DepositEntity> depositEntities = getUserDepositsByAccountId(accountId);
            return GetResponse.builder()
                    .success(true)
                    .gotObject(
                            convertDepositEntitiesToResponseList(depositEntities))
                    .build();
        }catch(InvalidAccountIdException e){
            throw e;
        }catch (EntityNotFoundException e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Deposits not Found for Account: " + accountId);
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
//return null;    
}
    
    

    @Override
    public GetResponse getAlLUserDepositsOfType(int accountId, int depositTypeId) {
        return null;
    }

    @Override
    public GetResponse getAllDeposits() {
        return null;
    }

    @Override
    public GetResponse getAllDepositsOfType(int depositTypeId) {
        return null;
    }
    
   private List<DepositEntity>  getUserDepositsByAccountId(int accountId ){
        try {
            return depositRepository.findByAccountId(accountId);
        }catch(Exception e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id Provided: " + accountId);
        }
//return null;  
        }
    private DepositEntity getDepositById(int accountId){
        try {
            Optional<DepositEntity> depositEntityOptional = depositRepository.findById(accountId);
            if (depositEntityOptional.isPresent()) return depositEntityOptional.get();
            else throw new EntityNotFoundException();
        }catch(Exception e){
            throw new InvalidAccountIdException(HttpStatus.BAD_REQUEST, "Invalid Account Id Provided: " + accountId);
        }
    }
    private DepositResponseObject convertDepositEntityToResponse(DepositEntity depositEntity) {
        return new DepositResponseObject(
        		depositEntity.getPk_deposit_id(),
        		depositEntity.getAccountEntity().getPk_account_id(),
        		depositEntity.getDepositTypeEntity().getName(),
        		depositEntity.getReference(),
        		depositEntity.getDate_deposit(),
        		depositEntity.getAmount()
        );
    }
    private List<DepositResponseObject> convertDepositEntitiesToResponseList(List<DepositEntity> depositEntities) {
        List<DepositResponseObject> responseObjects = new ArrayList<>(depositEntities.size());
        depositEntities.forEach(acc -> responseObjects.add(convertDepositEntityToResponse(acc)));
        return responseObjects;
    }
}
