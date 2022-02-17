import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class WithdrawGenerateService {
depositUrl = `http://localhost:8080/withdraw/details`
  constructor(
    private http: HttpClient
  ) { }

  createWithdraw(withdraw:WithdrawRequest){
    let postDepositUrl = `http://localhost:8080/withdraw/generate`;
    return this.http.post<PostWithdraw>(postDepositUrl, JSON.stringify({
      accountId: withdraw.accountId,
      requestType: withdraw.requestType,
      reference: withdraw.reference,
      amount: withdraw.amount
    }), httpOptions);
  }
}


