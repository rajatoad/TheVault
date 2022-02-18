import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { AccountService } from 'src/app/_services/account/account.service';
import { WithdrawGenerateService } from 'src/app/_services/transactions/withdraw-generate.service';

@Component({
  selector: 'app-withdraw-generate',
  templateUrl: './withdraw-generate.component.html',
  styleUrls: ['./withdraw-generate.component.css']
})
export class WithdrawGenerateComponent implements OnInit {

  @Output()
  submitEmitter = new EventEmitter<boolean>();

  constructor(
    private accountService: AccountService,
    private withdrawService: WithdrawGenerateService
  ) { }

  ngOnInit(): void {
  }

  onClickSubmit(amount:string, type:string, reference:string){
    let withdraw: WithdrawRequest = new WithdrawRequest(
      this.accountService.activeAccount.accountId,
      type,
      reference,
      Number.parseFloat(amount)
    );
    if(Number.parseFloat(amount) > this.accountService.getActiveAccount().availableBalance) {
      window.alert("TOO MUCH MONEY BUDDY");
      this.submitEmitter.emit(false);
      return;
    }

    this.withdrawService.createWithdraw(withdraw).subscribe(
      (data: PostWithdraw) => {
        let activeAccount = this.accountService.getActiveAccount();
        activeAccount.availableBalance -= data.createdObject[0].amount;
        activeAccount.pendingBalance -= data.createdObject[0].amount;
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
