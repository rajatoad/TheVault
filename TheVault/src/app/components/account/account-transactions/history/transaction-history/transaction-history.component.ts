import { Component, OnInit } from '@angular/core';
import { Deposit } from 'src/app/models/deposit.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
  
transactions: Deposit[] = [];
  constructor(private transService: TransactionHistoryService) { }
getHistory(): void{
  this.transService.getHistory().subscribe(transactions => this.transactions = transactions)
}
  ngOnInit(): void {
    this.getHistory();
  }

}
