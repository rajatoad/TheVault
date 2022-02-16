import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {

  httpOptions = {
    headers: new HttpHeaders ({'Content-Type': 'application/json'})
  }

  private transHistory = 'api/transactions'
  constructor(private http: HttpClient) { }
  getHistory(accountId:number) {
    let transactionUrl = `http://localhost:8080/transaction/history/${accountId}`;
    return this.http.get<GetTransaction>(transactionUrl)
  }
}
