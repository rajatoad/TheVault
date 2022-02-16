import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  @Input()
  account!: Account; 
  transactions: Transaction[] = [];
  constructor(private transService: TransactionHistoryService) { }

  ngOnInit(): void {
    this.getHistory();
  }

  getHistory(): void{
    this.transService.getHistory(this.account.accountId).subscribe(
      (data: GetTransaction) => this.transactions = data.gotObject
    )

  }


}
