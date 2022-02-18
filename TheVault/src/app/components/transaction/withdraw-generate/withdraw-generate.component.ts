import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

@Component({
  selector: 'app-withdraw-generate',
  templateUrl: './withdraw-generate.component.html',
  styleUrls: ['./withdraw-generate.component.css']
})
export class WithdrawGenerateComponent implements OnInit {

  @Output()
  submitEmitter = new EventEmitter<boolean>();

  constructor(
    private globalStorage: GlobalStorageService,
    private transactionHandler: TransactionHandlerService,
    private accountHandler: AccountHandlerService
  ) { }

  ngOnInit(): void {
  }

  onClickSubmit(amount:string, type:string, reference:string){
    let withdraw: WithdrawRequest = new WithdrawRequest(
      this.globalStorage.activeAccount.accountId,
      type,
      reference,
      Number.parseFloat(amount)
    );

    //Checks to make sure the withdraw request is not over the available balance
    //If it is then it closes with the submit emitter and returns
    if(Number.parseFloat(amount) > this.globalStorage.getActiveAccount().availableBalance) {
      window.alert("TOO MUCH MONEY BUDDY");
      this.submitEmitter.emit(false);
      return;
    }

    // Creates and updates the users profile on angular and Database
    this.transactionHandler.createWithdraw(withdraw).subscribe(this.createWithdrawObserver);
  }

  createWithdrawObserver = {
    next: (data:PostWithdraw) => {
      let activeAccount = this.globalStorage.getActiveAccount();
      activeAccount.availableBalance -= data.createdObject[0].amount;
      activeAccount.pendingBalance -= data.createdObject[0].amount;
      this.accountHandler.updateAccount(activeAccount).subscribe(this.updateAccountObserver);
    },
    error: (err: Error) => console.error("Create Withdraw Observer Error: " + err),
    complete: () => console.log("Successful creation of withdraw")
  }

  updateAccountObserver = {
    next: (data: PutAccount) => {
      this.globalStorage.setActiveAccount(data.updatedObject[0]);
      this.submitEmitter.emit(false);
    },
    error: (err: Error) => console.error("Update Account Observer Error: " + err),
    complete: () => console.log("Successfully updated account")
  }

}
