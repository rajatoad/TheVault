import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Observable, of } from 'rxjs';
import { Transaction } from 'src/app/models/transaction/transaction.model';
@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {
    transactionUrl = `http://ec2-18-234-126-118.compute-1.amazonaws.com:9000/transaction/history`;
    getUrl?: any
  httpOptions = {
    headers: new HttpHeaders ({'Content-Type': 'application/json'})
  }

  private transHistory = 'api/transactions'
  constructor(private http: HttpClient) { }
  getHistory(accountId:number) {
    this.getUrl = `${this.transactionUrl}/${accountId}`
    return this.http.get<GetTransaction>(this.getUrl)
  }
 
}
