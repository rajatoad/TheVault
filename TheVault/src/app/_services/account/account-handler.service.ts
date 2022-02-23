import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { DeleteAccount } from 'src/app/models/account/responses/delete-account';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { TransferRequest } from 'src/app/models/transaction/request/transfer-request.model';

const AUTH_API = 'ec2-18-234-126-118.compute-1.amazonaws.com:9000';


const ENDPOINTS = {
  CREATE_ACCOUNT: `${AUTH_API}account/create`,
  UPDATE_ACCOUNT: `${AUTH_API}account/update`,
  GET_ACCOUNT: `${AUTH_API}account/users-accounts?userId=`,
  DELETE_ACCOUNT: `${AUTH_API}account/delete?accountId=`,
  TRANSFER: `${AUTH_API}account/transfer`
}

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root'
})
export class AccountHandlerService {

  constructor(
    private http: HttpClient
  ) { }

  createAccount(userId:number, accountType:string){
    return this.http.post<PostAccount>(
      ENDPOINTS.CREATE_ACCOUNT, 
      JSON.stringify(
        {
          userId: userId,
          accountType: accountType
        }
      ), httpOptions);
  }

  updateAccount(updateAccount:Account){
    return this.http.put<PutAccount>(
      ENDPOINTS.UPDATE_ACCOUNT, 
      JSON.stringify(
        {
          accountId: updateAccount.accountId,
          accountType: updateAccount.accountType,
          availableBalance: updateAccount.availableBalance,
          pendingBalance: updateAccount.pendingBalance
        }
      ),httpOptions);
  }

  getAccounts(userId:number){
    return this.http.get<GetAccount>(
      `${ENDPOINTS.GET_ACCOUNT + userId}`
    );
  }

  deleteAccount(account:Account){
    return this.http.delete<DeleteAccount>(
      `${ENDPOINTS.DELETE_ACCOUNT + account.accountId}`, httpOptions);
  }
  
  createTransfer(transfer: TransferRequest){
    return this.http.put<PutAccount>(ENDPOINTS.TRANSFER, JSON.stringify({
      ownerAccountId: transfer.ownerAccountId,
      receiverAccountId: transfer.receiverAccountId,
      amount: transfer.amount
    }), httpOptions);
  }

}
