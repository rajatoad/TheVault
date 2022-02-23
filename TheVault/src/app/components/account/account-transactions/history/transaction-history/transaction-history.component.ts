import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Transaction } from 'src/app/models/transaction/transaction.model';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
  
  /* istanbul ignore next */
  term!:string;
  /* istanbul ignore next */
  index?:number
  
  /* istanbul ignore next */
  @Input()
  account!: Account;
  
  /* istanbul ignore next */
  @Input()
  transactions: Transaction[] = [];

  /* istanbul ignore next */
  constructor() { }

  /* istanbul ignore next */
  ngOnInit(): void {
  }
}
