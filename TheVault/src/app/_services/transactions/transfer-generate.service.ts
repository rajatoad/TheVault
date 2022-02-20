import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransferRequest } from './../../models/transaction/request/transfer-request.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TransferGenerateService {

  constructor(
    private http: HttpClient,
  ) { }

  createTransfer(transfer: TransferRequest) {
    let putTransferUrl = `http://localhost:8080/account/transfer`;
    console.log(transfer);
    return this.http.put<TransferMoney>(putTransferUrl, JSON.stringify({
      ownerAccountId: transfer.accountId,
      receiverAccountId: transfer.receiverAccountId,
      amount: transfer.amount
    }), httpOptions);
  }
}
