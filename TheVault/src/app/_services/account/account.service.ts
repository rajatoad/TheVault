import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { AccountRetrieverService } from '../backend/account-retriever.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountTypes: string[] = ["Checking", "Savings"];

  activeAccount!: Account;

  getAccount!: GetAccount;

  constructor(
    private accountHttp: AccountRetrieverService
  ) { }

  setActiveAccount(account:Account):void{
    this.activeAccount = account;
  }

  getActiveAccount():Account{
    return this.activeAccount;
  }

}
