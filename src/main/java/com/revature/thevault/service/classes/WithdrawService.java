package com.revature.thevault.service.classes;

import com.revature.thevault.presentation.model.request.WithdrawRequest;
import com.revature.thevault.presentation.model.response.builder.DeleteResponse;
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
    }

    @Override
    public GetResponse getAllUserWithdrawals(int accountId) {
        List<WithdrawEntity> withdrawEntities = findByAccountId(accountId);
        return GetResponse.builder()
                .success(true)
                .gotObject(
                        convertEntityListToResponses(withdrawEntities)
                )
                .build();
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
        return GetResponse.builder()
                .success(true)
                .gotObject(
                        convertEntityListToResponses(
                                findByAccountIdAndRequestType(accountId, requestName)
                        )
                )
                .build();
    }

    @Override
    public GetResponse findByWithdrawId(int withdrawId) {
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
    }

    @Override
    public DeleteResponse deleteAllWithdraws(Integer accountId) {
        withdrawRepository.deleteByAccountentity(new AccountEntity(accountId, new LoginCredentialEntity(), new AccountTypeEntity(), 0, 0));
        return DeleteResponse.builder()
                .success(true)
                .deletedObject(Collections.EMPTY_LIST)
                .build();
    }

    private List<WithdrawEntity> findByAccountIdAndRequestType(int accountId, String requestName) {
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
