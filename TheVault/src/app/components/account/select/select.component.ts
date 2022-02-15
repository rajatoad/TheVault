import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountService } from 'src/app/_services/account/account.service';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  @Input()
  userId!:number;

  constructor(
    private accountService: AccountService
  ) { }

  accounts!: Account[];

  ngOnInit(): void {
    this.getAccounts();
  }

  getAccounts():void{
    this.accounts = this.accountService.getAccounts(this.userId);
  }

}
