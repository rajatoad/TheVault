import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Transaction } from 'src/app/models/transaction/transaction.model';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
  
  term!:string;
  index?:number
  
  @Input()
  account!: Account;
  
  @Input()
  transactions: Transaction[] = [];

  constructor() { }

  ngOnInit(): void {
  }
}
