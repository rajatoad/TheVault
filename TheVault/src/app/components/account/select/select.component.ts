import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { AccountService } from 'src/app/_services/account/account.service';
import { AccountRetrieverService } from 'src/app/_services/backend/account-retriever.service';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  @Input()
  userId!:number;

  constructor(
    private accountService: AccountService,
    private accountHttp: AccountRetrieverService
  ) { }

  accounts!: Account[];

  ngOnInit(): void {
    this.getAccounts();
  }

  getAccounts():void{
    this.accountHttp.getUserAccount(this.userId)
    .subscribe(
      (data: GetAccount) => this.accounts = data.gotObject)};
}
