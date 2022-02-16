import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountService } from 'src/app/_services/account/account.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';
import { UserService } from 'src/app/_services/user/user.service';

@Component({
  selector: 'app-account-detail',
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  account!: Account;

  constructor(
    private accountService: AccountService,
    private location: Location,
    private router: RoutingAllocatorService,
    private userSession: UserSessionService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.account = this.accountService.activeAccount;
  }

  goBack(){
    this.location.back();
  }

  logout(){
    this.router.login();
  }

}
