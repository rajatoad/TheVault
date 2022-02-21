import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  account!: Account;
  transactions: Transaction[] = [];
  createDeposit: boolean = false;
  createWithdraw: boolean = false;

  createTransfer: boolean = false;

  constructor(
    private globalStorage: GlobalStorageService,
    private transactionHandler: TransactionHandlerService,
    private location: Location,
    private router: RoutingAllocatorService
  ) { }

  ngOnInit(): void {
    this.setupPage();
  }

  setupPage(){
    this.account = this.globalStorage.getActiveAccount();
    this.getTransactions();
  }

  // Populate the transaction history of the account
  getTransactions(){
    this.transactionHandler.getTransactionHistory(this.account.accountId).subscribe(this.getTransactionObserver)
  }

  //Deposit and Withdraw generator function used to display the components related to create them
  depositGenerator(){
    this.createWithdraw = false;
    this.createTransfer = false;
    this.createDeposit = !this.createDeposit;
  }

  withdrawGenerator(){
    this.createDeposit = false;
    this.createTransfer = false;
    this.createWithdraw = !this.createWithdraw;
  }

  transferGenerator() {
    this.createDeposit = false;
    this.createWithdraw = false;
    this.createTransfer = !this.createTransfer;
  }

  // Submit events are used to close the component and to refresh the transaction history
  depositSubmitEvent(submit: boolean) {
    this.createDeposit = submit;
    this.getTransactions();
  }

  withdrawSubmitEvent(submit: boolean) {
    this.createWithdraw = submit;
    this.getTransactions();
  }

  transferSubmitEvent(submit: boolean) {
    this.createTransfer = submit;
    this.ngOnInit();
  }

  logout(){
    this.router.login();
  }

  goBack(){
    this.location.back();
  }

  getTransactionObserver = {
    next: (data: GetTransaction) => this.transactions = data.gotObject,
    error: (err: Error) => console.error("Get Transaction Observer Error: " + err),
    complete: () => console.log("Got Transaction History Successfully")
  }

}
