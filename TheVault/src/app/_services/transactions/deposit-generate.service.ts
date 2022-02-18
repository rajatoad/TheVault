import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { DeleteDeposit } from 'src/app/models/transaction/responses/delete-deposit';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DepositGenerateService {

  constructor(
    private http: HttpClient
  ) { }

  createDeposit(deposit:DepositRequest){
    let postDepositUrl = `http://localhost:8080/deposit/create`;
    console.log(deposit);
    return this.http.post<PostDeposit>(postDepositUrl, JSON.stringify({
      depositType: deposit.depositType,
      accountId: deposit.accountId,
      reference: deposit.reference,
      amount: deposit.amount

    }), httpOptions);
  }

  deleteAllDeposits(accountId:number){
    let deleteAllDepositsUrl = `http://localhost:8080/deposit/clear/${accountId}`;
    return this.http.delete<DeleteDeposit>(deleteAllDepositsUrl, httpOptions);
  }
}
