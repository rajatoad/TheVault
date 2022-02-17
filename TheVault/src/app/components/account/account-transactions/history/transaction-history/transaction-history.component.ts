import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
collapseCounter =0;
  @Input()
  account!: Account; 
  @Input()
  transactions: Transaction[] = [];
  constructor(
    private transService: TransactionHistoryService,
    private router: Router
  ) { }
acknowledge(){
  console.log("hi how are ya")
  this.collapseCounter = 1;
}
rickRoll():void{this.router.navigateByUrl('https://www.youtube.com/watch?v=dQw4w9WgXcQ')}

  ngOnInit(): void {
  }


}
