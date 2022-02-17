import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { AccountRetrieverService } from '../backend/account-retriever.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountTypes: string[] = ["Checking", "Savings"];

  activeAccount!: Account;

  getAccount!: GetAccount;

  constructor(
    private accountHttp: AccountRetrieverService,
    private http: HttpClient
  ) { }

  setActiveAccount(account:Account):void{
    this.activeAccount = account;
  }

  getActiveAccount():Account{
    return this.activeAccount;
  }

  updateAccount(updateAccount:Account){

    let updateAccountUrl = `http://localhost:8080/account/update`;
    console.log(updateAccount);
    return this.http.put<PutAccount>(updateAccountUrl, JSON.stringify({
      accountId: updateAccount.accountId,
      accountType: updateAccount.accountType,
      availableBalance: updateAccount.availableBalance,
      pendingBalance: updateAccount.pendingBalance
    }), httpOptions);
  }

}
