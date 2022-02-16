import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  @Input()
  account!:Account;
  
  constructor() { }

  ngOnInit(): void {
  }

}
