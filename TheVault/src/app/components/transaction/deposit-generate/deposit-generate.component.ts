import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { Deposit } from 'src/app/models/transaction/deposit.model';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { AccountService } from 'src/app/_services/account/account.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { DepositGenerateService } from 'src/app/_services/transactions/deposit-generate.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';

@Component({
  selector: 'app-deposit-generate',
  templateUrl: './deposit-generate.component.html',
  styleUrls: ['./deposit-generate.component.css']
})
export class DepositGenerateComponent implements OnInit {

  @Output()
  submitEmitter = new EventEmitter<boolean>();

  constructor(
    private accountService: AccountService,
    private depositService: DepositGenerateService,
  ) { }

  ngOnInit(): void {
  }

  onClickSubmit(amount:string, type:string, reference:string){
    console.log(this.accountService.activeAccount);
    let deposit: DepositRequest = new DepositRequest(type, this.accountService.activeAccount.accountId, reference, Number.parseFloat(amount));
    console.log(deposit);
    this.depositService.createDeposit(deposit).subscribe(
      (data: PostDeposit) => {
        console.log(data);
        let activeAccount = this.accountService.getActiveAccount();
        activeAccount.availableBalance += data.createdObject[0].amount;
        activeAccount.pendingBalance += data.createdObject[0].amount;
        this.accountService.updateAccount(activeAccount).subscribe(
          (data: PutAccount) => {
            console.log(data);
            this.accountService.activeAccount = data.updatedObject[0];
          }
        )
        this.submitEmitter.emit(false);
      }
    );
  }

}
