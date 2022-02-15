import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountService } from 'src/app/_services/account/account.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  account!: Account;

  constructor(
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
    this.account = this.accountService.activeAccount;
  }

}
