import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountService } from 'src/app/_services/account/account.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';

@Component({
  selector: 'app-account-mini',
  templateUrl: './account-mini.component.html',
  styleUrls: ['./account-mini.component.css']
})
export class AccountMiniComponent implements OnInit {

  constructor(
    private routingAllocator: RoutingAllocatorService,
    private accountService: AccountService
  ) { }

  @Input()
  account!: Account;

  ngOnInit(): void {
    console.log("hello");
  }

  goToDetail(account:Account){
    this.accountService.setActiveAccount(account);
    this.routingAllocator.detail();
  }

}
