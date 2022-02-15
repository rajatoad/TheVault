import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountTypes: string[] = ["Checking", "Savings"];

  activeAccount!: Account;

  constructor() { }

  getAccounts(userId:number): Account[]{
    return [
      new Account(1, 1, this.accountTypes[0], 100, 100),
      new Account(2, 1, this.accountTypes[1], 120, 300)
    ]
  }

  setActiveAccount(account:Account):void{
    this.activeAccount = account;
  }
}
