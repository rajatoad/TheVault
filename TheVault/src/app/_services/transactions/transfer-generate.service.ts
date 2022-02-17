import { TransferMoney } from './../../models/transaction/responses/transfer';
import { TransferRequest } from './../../models/transaction/request/transfer-request.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { TransferGenerateComponent } from 'src/app/components/transaction/transfer-generate/transfer-generate.component';
import { Transfer } from 'src/app/models/transaction/transfer.model';

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
    let postTransferUrl = `http://localhost:8080/account/users-accounts?userId=1`;
    console.log(transfer);
    return this.http.post<TransferMoney>(postTransferUrl, JSON.stringify({
      transferAmount: transfer.amount,
      transferOwner: transfer.ownerAccountId,
      transferReciever: transfer.receiverAccountId
    }), httpOptions);
  }
}
