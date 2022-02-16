package com.revature.thevault.service.classes;


import com.revature.thevault.presentation.model.request.DepositRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.DepositRepository;
import com.revature.thevault.repository.entity.*;
import com.revature.thevault.service.dto.DepositResponseObject;
import com.revature.thevault.service.exceptions.InvalidAccountIdException;
import com.revature.thevault.service.exceptions.InvalidDepositIdException;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.interfaces.DepositServiceInterface;

import java.sql.Date;
import java.time.LocalDate;
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

   @Override
   public PostResponse createDeposit(DepositRequest depositRequest){
       try{
           return PostResponse.builder()
                   .success(true)
                   .createdObject(Collections.singletonList(
                           convertDepositEntityToResponse(depositRepository.save(
                                   new DepositEntity(
                                           0,
                                            new AccountEntity(depositRequest.getAccountId(), new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
                                           depositTypeService.findDepositTypeEntityByName(depositRequest.getDepositType()),
                                           depositRequest.getReference(),
                                           Date.valueOf(LocalDate.now()),
                                           depositRequest.getAmount()
                                   )
                           )
                   )))
                   .build();
       }catch(Exception e){
           throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Bad request");
       }
   }

    @Override
    public GetResponse getAllUserDeposits(int accountId) {
        try {
            List<DepositEntity> depositEntities = getUserDepositsByAccountId(accountId);
            return GetResponse.builder()
                    .success(true)
                    .gotObject(convertDepositEntitiesToResponseList(depositEntities))
                    .build();
        }catch(InvalidAccountIdException e){
            throw e;
        }catch (EntityNotFoundException e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Deposits not Found for Account: " + accountId);
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Invalid Request");
        }
    }

    @Override
    public GetResponse getAlLUserDepositsOfType(int accountId, String depositType) {
       try{
           List<DepositEntity> depositEntities = getUserDepositsByAccountIdAndType(accountId, depositTypeService.findDepositTypeEntityByName(depositType));
           return GetResponse.builder()
                   .success(true)
                   .gotObject(convertDepositEntitiesToResponseList(depositEntities))
                   .build();
       }catch(Exception e){
           throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
       }
    }

    @Override
    public GetResponse findByDepositId(int depositId) {
        try{
            Optional<DepositEntity> depositEntityOptional =depositRepository.findById(depositId);
            if(depositEntityOptional.isPresent())
            return GetResponse.builder()
                    .success(true)
                    .gotObject(
                            Collections.singletonList(convertDepositEntityToResponse(depositEntityOptional.get())
                    ))
                    .build();
            else throw new InvalidDepositIdException(HttpStatus.BAD_REQUEST, "Deposit not found: " + depositId);
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private List<DepositEntity> getUserDepositsByAccountIdAndType(int accountId, DepositTypeEntity depositTypeEntity) {
        return depositRepository.findByAccountentityAndDeposittypeentity(
                new AccountEntity(accountId, new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
                depositTypeEntity);
    }

    private List<DepositEntity> getUserDepositsByAccountId(int accountId) {
        return depositRepository.findByAccountentity(new AccountEntity(
                accountId,
                new LoginCredentialEntity(),
                new AccountTypeEntity(),
                0,
                0
        ));
    }

    private DepositResponseObject convertDepositEntityToResponse(DepositEntity depositEntity) {
        return new DepositResponseObject(
        		depositEntity.getPk_deposit_id(),
        		depositEntity.getAccountentity().getPk_account_id(),
        		depositEntity.getDeposittypeentity().getName(),
        		depositEntity.getReference(),
        		depositEntity.getDate_deposit().toLocalDate(),
        		depositEntity.getAmount()
        );
    }
    private List<DepositResponseObject> convertDepositEntitiesToResponseList(List<DepositEntity> depositEntities) {
        List<DepositResponseObject> responseObjects = new ArrayList<>(depositEntities.size());
        depositEntities.forEach(acc -> responseObjects.add(convertDepositEntityToResponse(acc)));
        return responseObjects;
    }
}
