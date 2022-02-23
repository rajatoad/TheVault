import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { TransferRequest } from 'src/app/models/transaction/request/transfer-request.model';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { DeleteDeposit } from 'src/app/models/transaction/responses/delete-deposit';
import { DeleteWithdraw } from 'src/app/models/transaction/responses/delete-withdraw';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { Withdraw } from 'src/app/models/transaction/withdraw.model';


const AUTH_API = 'ec2-18-234-126-118.compute-1.amazonaws.com:9000';

const ENDPOINTS = {
  CREATE_DEPOSIT: `${AUTH_API}deposit/create`,
  CREATE_WITHDRAW: `${AUTH_API}withdraw/generate`,
  DELETE_ALL_DEPOSIT: `${AUTH_API}deposit/clear/`,
  DELETE_ALL_WITHDRAW: `${AUTH_API}withdraw/clear/`,
  TRANSACTION_DETAILS: `${AUTH_API}withdraw/detail/`,
  TRANSACTION_HISTORY: `${AUTH_API}transaction/history/`
}

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TransactionHandlerService {

  constructor(
    private http: HttpClient
  ) { }
  
  getTransactionDetails(withdrawId:number){
    return this.http.get<Withdraw>(
      `${ENDPOINTS.TRANSACTION_DETAILS + withdrawId}`
    )
  }

  createDeposit(deposit:DepositRequest){
    return this.http.post<PostDeposit>(
      ENDPOINTS.CREATE_DEPOSIT, 
      JSON.stringify(
        {
          depositType: deposit.depositType,
          accountId: deposit.accountId,
          reference: deposit.reference,
          amount: deposit.amount
        }
      ), httpOptions);
  }

  deleteAllDeposits(accountId:number){
    return this.http.delete<DeleteDeposit>(
      `${ENDPOINTS.DELETE_ALL_DEPOSIT + accountId}`, httpOptions);
  }

  createWithdraw(withdraw:WithdrawRequest){
    console.log(withdraw)
    return this.http.post<PostWithdraw>(
      ENDPOINTS.CREATE_WITHDRAW, 
      JSON.stringify(
        {
          accountId: withdraw.accountId,
          requestType: withdraw.requestType,
          reference: withdraw.reference,
          amount: withdraw.amount
        }
      ), httpOptions);
  }

  deleteAllWithdraws(accountId:number){
    return this.http.delete<DeleteWithdraw>(`${ENDPOINTS.DELETE_ALL_WITHDRAW + accountId}`, httpOptions)
  }

  getTransactionHistory(accountId:number){
    return this.http.get<GetTransaction>(`${ENDPOINTS.TRANSACTION_HISTORY + accountId}`)
  }

}
