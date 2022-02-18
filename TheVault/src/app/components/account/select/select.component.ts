import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { DeleteAccount } from 'src/app/models/account/responses/delete-account';
import { DeleteDeposit } from 'src/app/models/transaction/responses/delete-deposit';
import { DeleteWithdraw } from 'src/app/models/transaction/responses/delete-withdraw';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  @Input()
  userId!:number;

  @Input()
  accounts!: Account[];

  deletingAccount!: Account;
  deleteAccountIndex!: number;

  constructor(
    private globalStorage: GlobalStorageService,
    private accountHandler: AccountHandlerService,
    private transactionHandler: TransactionHandlerService 
  ) { }


  ngOnInit(): void {
    this.setupAccounts();
  }

  setupAccounts(){
    this.accounts = this.globalStorage.getAccounts();
  }

  // Deleting account must be done in this order due to deposits being linked to a the deposit account
  // deposits and withdraws must be removed and then accounts can be deleted
  deleteAccount(account:Account, index:number){
    this.deletingAccount = account;
    this.deleteAccountIndex = index;
    this.transactionHandler.deleteAllDeposits(account.accountId).subscribe(this.deleteDepositObserver);
  }

  deleteDepositObserver = {
    next: (data: DeleteDeposit) => {
      this.transactionHandler.deleteAllWithdraws(this.deletingAccount.accountId)
      .subscribe(this.deleteWithdrawObserver)
    },
    error: (err: Error) => console.error("Delete Deposit Observer error: " + err),
    complete: () => console.log("Delete All Deposits Successful")
  }

  deleteWithdrawObserver = {
    next: (data: DeleteWithdraw) => {
      this.accountHandler.deleteAccount(this.deletingAccount)
      .subscribe(this.deleteAccountObserver)
    },
    error: (err: Error) => console.error("Delete withdraw observer error: " + err),
    complete: () => console.log("Delete All Withdraws Successful")
  }

  deleteAccountObserver = {
    next: (data: DeleteAccount) => {
      window.alert(`${this.deletingAccount} GOODBYE`);
      this.accounts.slice(this.deleteAccountIndex, 1);
    },
    error: (err: Error) => console.log("Delete Account Observer error: " + err),
    complete: () => console.log("Delete Account successful")
  }
}
