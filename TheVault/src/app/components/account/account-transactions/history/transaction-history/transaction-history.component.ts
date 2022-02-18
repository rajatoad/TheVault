import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
import { Router } from '@angular/router';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

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
  constructor(
    private transService: TransactionHistoryService,
    private router: Router,
    private transactionHandler: TransactionHandlerService,
  ) { }
showDetails(tran:Transaction){
 console.log(tran);
}
rickRoll():void{this.router.navigateByUrl('https://www.youtube.com/watch?v=dQw4w9WgXcQ')}

  ngOnInit(): void {
  }


}
