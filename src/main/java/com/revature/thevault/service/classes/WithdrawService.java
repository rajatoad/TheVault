package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.GetResponse;
import com.revature.thevault.presentation.model.response.builder.PostResponse;
import com.revature.thevault.repository.dao.WithdrawRepository;
import com.revature.thevault.repository.entity.*;
import com.revature.thevault.service.dto.WithdrawResponseObject;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import com.revature.thevault.service.exceptions.InvalidWithdrawIdRequest;
import com.revature.thevault.service.interfaces.WithdrawServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("withdrawService")
public class WithdrawService implements WithdrawServiceInterface {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private RequestTypeService requestTypeService;

    @Autowired
    private RequestStatusService requestStatusService;

    @Override
    public PostResponse createWithdrawal(WithdrawRequest withdrawRequest) {
        try{
            return PostResponse.builder()
                    .success(true)
                    .createdObject(
                            Collections.singletonList(
                                    convertEntityToResponse(
                                            withdrawRepository.save(
                                                    new WithdrawEntity(
                                                            0,
                                                            new AccountEntity(withdrawRequest.getAccountId(), new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0),
                                                            requestTypeService.getRequestTypeByName(withdrawRequest.getRequestType()),
                                                            requestStatusService.getRequestStatusByName("Pending"),
                                                            withdrawRequest.getReference(),
                                                            Date.valueOf(LocalDate.now()),
                                                            withdrawRequest.getAmount()
                                                    )
                                            )
                                    )
                            )
                    )
                    .build();
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @Override
    public GetResponse getAllUserWithdrawals(int accountId) {
        try{
            List<WithdrawEntity> withdrawEntities = findByAccountId(accountId);
            return GetResponse.builder()
                    .success(true)
                    .gotObject(
                            convertEntityListToResponses(withdrawEntities)
                    )
                    .build();
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private List<WithdrawResponseObject> convertEntityListToResponses(List<WithdrawEntity> withdrawEntities) {
        List<WithdrawResponseObject> withdrawResponseObjects = new ArrayList<>(withdrawEntities.size());
        withdrawEntities.forEach(withdraw -> withdrawResponseObjects.add(convertEntityToResponse(withdraw)));
        return withdrawResponseObjects;
    }

    private List<WithdrawEntity> findByAccountId(int accountId) {
        return withdrawRepository.findByAccountentity(
                new AccountEntity(
                        accountId,
                        new LoginCredentialEntity(),
                        new AccountTypeEntity(),
                        0,
                        0
                )
        );
    }

    @Override
    public GetResponse getAlLUserWithdrawalsOfType(int accountId, String requestName) {
        try{
            return GetResponse.builder()
                    .success(true)
                    .gotObject(
                            convertEntityListToResponses(
                                    findByAccountIdAndRequestType(accountId, requestName)
                            )
                    )
                    .build();
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public GetResponse findByWithdrawId(int withdrawId) {
        try{
            Optional<WithdrawEntity> withdrawEntityOptional = withdrawRepository.findById(withdrawId);
            if(withdrawEntityOptional.isPresent())
                return GetResponse.builder()
                        .success(true)
                        .gotObject(Collections.singletonList(
                                convertEntityToResponse(withdrawEntityOptional.get())
                        ))
                        .build();
            else
                throw new InvalidWithdrawIdRequest(HttpStatus.BAD_REQUEST, "Withdraw not found, withdraw Id: " + withdrawId);
        }catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private List<WithdrawEntity> findByAccountIdAndRequestType(int accountId, String requestName) {
        try{
            return withdrawRepository.findByAccountentityAndRequesttypeentity(
                    new AccountEntity(
                            accountId,
                            new LoginCredentialEntity(),
                            new AccountTypeEntity(),
                            0,
                            0
                    ),
                    requestTypeService.getRequestTypeByName(requestName)
            );
        }catch(Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private WithdrawResponseObject convertEntityToResponse(WithdrawEntity withdrawEntity) {
        return new WithdrawResponseObject(
                withdrawEntity.getPk_withdraw_id(),
                withdrawEntity.getAccountentity().getPk_account_id(),
                withdrawEntity.getRequesttypeentity().getName(),
                withdrawEntity.getRequeststatusentity().getName(),
                withdrawEntity.getReference(),
                withdrawEntity.getDate_withdraw().toLocalDate(),
                withdrawEntity.getAmount()
        );
    }
}
