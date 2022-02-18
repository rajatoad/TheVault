import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { DeleteAccount } from 'src/app/models/account/responses/delete-account';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { RoutingAllocatorService } from '../app_control/routing-allocator.service';
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

  accounts!: Account[];

  constructor(
    private accountHttp: AccountRetrieverService,
    private http: HttpClient,
    private router: RoutingAllocatorService
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

  createAccount(userId:number, accountType:string){
    let createAccountUrl = "http://localhost:8080/account/create";
    return this.http.post<PostAccount>(createAccountUrl, JSON.stringify({
      userId: userId,
      accountType: accountType
    }), httpOptions);
  }

  getUserAccounts(userId:number){
    this.accountHttp.getUserAccount(userId)
    .subscribe(
      (data: GetAccount) => {
        this.accounts = data.gotObject;
        this.router.accountView();
      }
    )
  }

  getStoredAccounts(){
    console.log(this.accounts);
    return this.accounts;
  }

  addAccount(account:Account){
    this.accounts.push(account);
  }

  deleteAccount(account:Account){
    let deleteAccountUrl = `http://localhost:8080/account/delete?accountId=${account.accountId}`;
    return this.http.delete<DeleteAccount>(deleteAccountUrl, httpOptions);
  }

}
