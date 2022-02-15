import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountDetailComponent } from '../account-detail/account-detail.component';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
import { Deposit } from 'src/app/models/deposit.model';
import { Account } from 'src/app/models/account/account.model';
import { AccountService } from 'src/app/_services/account/account.service'; 
@Component({
  selector: 'app-account-transactions',
  templateUrl: './account-transactions.component.html',
  styleUrls: ['./account-transactions.component.css']
})
export class AccountTransactionsComponent implements OnInit {

  transactions: Deposit[]= [];

newTrans(reason:string, amount:string):void{
// data: Deposit  = {
//   depositid: 0,
//   amount: 0,
//   depositDate: 0,
//   reference: '',
//   accountid: 0
// }
}  

  // constructor(private router: Router) { }
constructor(private transService: TransactionHistoryService){}

ngOnInit(): void {
    // setTimeout(() => {
    //   this.router.navigate(['/account-view'])
    // }, 6000);
  }
  
}
