import { Injectable } from '@angular/core';
import { Account } from '../models/account/account.model';
import { GetAccount } from '../models/account/responses/get-account';
import { Profile } from '../models/users/profile.model';

@Injectable({
  providedIn: 'root'
})
export class GlobalStorageService {

  userId!: number;
  userProfile!: Profile;
  username!: string;

  accountTypes: string[] = ["Checking", "Savings"];
  activeAccount!: Account;
  getAccount!: GetAccount;
  accounts!: Account[];

  constructor() { }

  public setProfile(user: Profile): void {this.userProfile = user}

  public getProfile():Profile{return this.userProfile}

  public setUsername(username:string){this.username = username}

  public getUsername():string{return this.username}

  public setUserId(userId: number){this.userId = userId}

  public getUserId():number{return this.userId}

  public setAccounts(accounts: Account[]){this.accounts = accounts}

  public getAccounts(): Account[]{return this.accounts}
  
  public addAccount(account:Account){this.accounts.push(account)}

  public setActiveAccount(account:Account){this.activeAccount = account}

  public getActiveAccount():Account{return this.activeAccount}

}
