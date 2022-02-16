import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Deposit } from 'src/app/models/deposit.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  @Input()
  account!: Account; 
  transactions: Deposit[] = [];
  constructor(private transService: TransactionHistoryService) { }

  getHistory(): void{
    this.transService.getHistory().subscribe(transactions => this.transactions = transactions);
  }

  ngOnInit(): void {
    this.getHistory();
  }

}
