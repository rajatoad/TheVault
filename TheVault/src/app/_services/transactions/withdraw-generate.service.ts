import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import {Withdraw} from 'src/app/models/transaction/withdraw.model';
import { Observable } from 'rxjs';
import { DeleteWithdraw } from 'src/app/models/transaction/responses/delete-withdraw';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class WithdrawGenerateService {
  
depositUrl = `http://localhost:8080/withdraw`
  constructor(
    private http: HttpClient
  ) { }
    getTransactionDetails(withdrawId:number):Observable<Withdraw>{
      let getDetailUrl = `${this.depositUrl}/detail/${withdrawId}`
      return this.http.get<Withdraw>(getDetailUrl)
    }
  createWithdraw(withdraw:WithdrawRequest){
    let postDepositUrl = `${this.depositUrl}/generate`;
    return this.http.post<PostWithdraw>(postDepositUrl, JSON.stringify({
      accountId: withdraw.accountId,
      requestType: withdraw.requestType,
      reference: withdraw.reference,
      amount: withdraw.amount
    }), httpOptions);
  }

  deleteAllWithdraws(accountId:number){
    let deleteAllWithdrawsUrl = `http://localhost:8080/withdraw/clear/${accountId}`;
    return this.http.delete<DeleteWithdraw>(deleteAllWithdrawsUrl, httpOptions);
  }
}


