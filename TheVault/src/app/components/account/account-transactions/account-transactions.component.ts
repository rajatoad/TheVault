import { Component, OnInit } from '@angular/core';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
@Component({
  selector: 'app-account-transactions',
  templateUrl: './account-transactions.component.html',
  styleUrls: ['./account-transactions.component.css']
})
export class AccountTransactionsComponent implements OnInit {
  constructor(private transService: TransactionHistoryService){}
  ngOnInit(): void {}
}
