import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { AccountService } from 'src/app/_services/account/account.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';
import { UserService } from 'src/app/_services/user/user.service';

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

  constructor(
    private accountService: AccountService,
    private location: Location,
    private router: RoutingAllocatorService,
    private userSession: UserSessionService,
    private userService: UserService,
    private transService: TransactionHistoryService
  ) { }

  ngOnInit(): void {
    this.account = this.accountService.activeAccount;
    this.updateTransactions();
  }

  updateTransactions(){
    this.transService.getHistory(this.account.accountId).subscribe(
      (data: GetTransaction) => this.transactions = data.gotObject
    )
  }

  goBack(){
    this.location.back();
  }

  depositGenerator(){
    this.createWithdraw = false;
    this.createDeposit = !this.createDeposit;
  }

  withdrawGenerator(){
    this.createDeposit = false;
    this.createWithdraw = !this.createWithdraw;
  }

  depositSubmitEvent(submit:boolean){
    this.createDeposit = submit;
    this.ngOnInit();
  }

  withdrawSubmitEvent(submit:boolean){
    this.createWithdraw = submit;
    this.ngOnInit();
  }

  logout(){
    this.router.login();
  }

}
