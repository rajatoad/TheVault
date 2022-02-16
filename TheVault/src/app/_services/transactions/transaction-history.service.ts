import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deposit } from 'src/app/models/deposit.model';
@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {

  httpOptions = {
    headers: new HttpHeaders ({'Content-Type': 'application/json'})
  }

  private transHistory = 'api/transactions'
  constructor(private http: HttpClient) { }
  getHistory(): Observable<Deposit[]> {return this.http.get<Deposit[]>(this.transHistory)}

  addHistory(): Observable<Deposit> {
    return this.http.post<Deposit>(this.transHistory,this.httpOptions)
  } 
}
