import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GetAccount } from 'src/app/models/account/responses/get-account';

@Injectable({
  providedIn: 'root'
})
export class AccountRetrieverService {

  constructor(
    private http: HttpClient
  ) { }

  accountUrl = 'http://localhost:8080/account'

  getUserAccount(userId: number){
    let userAccountUrl: string = `${this.accountUrl}/users-accounts?userId=${userId}`;
    return this.http.get<GetAccount>(userAccountUrl);
  }

}
