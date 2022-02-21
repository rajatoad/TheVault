import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

@Component({
  selector: 'app-account-mini',
  templateUrl: './account-mini.component.html',
  styleUrls: ['./account-mini.component.css']
})
export class AccountMiniComponent implements OnInit {

  constructor(
    private routingAllocator: RoutingAllocatorService,
    private globalStorage: GlobalStorageService
  ) { }

  @Input()
  account!: Account;

  ngOnInit(): void {
  }

  goToDetail(account:Account){
    this.globalStorage.setActiveAccount(account);
    this.routingAllocator.detail();
  }

}
