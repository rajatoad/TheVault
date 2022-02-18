import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { TransactionHistoryService } from 'src/app/_services/transactions/transaction-history.service';
@Component({
  selector: 'app-transaction-search',
  templateUrl: './transaction-search.component.html',
  styleUrls: ['./transaction-search.component.css']
})
export class TransactionSearchComponent implements OnInit {
  

  constructor() { }
  ngOnInit(): void {
  
  }
  }

 

