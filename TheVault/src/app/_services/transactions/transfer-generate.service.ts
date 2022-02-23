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
    private http: HttpClient
  ) { }

  createTransfer(transfer: TransferRequest) {
    let putTransferUrl = `ec2-18-234-126-118.compute-1.amazonaws.com:9000/account/transfer`;
    console.log(transfer);
    return this.http.put<TransferRequest>(putTransferUrl, JSON.stringify({
      ownerAccountId: transfer.ownerAccountId,
      receiverAccountId: transfer.receiverAccountId,
      amount: transfer.amount
    }), httpOptions);
  }
}
