import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

@Component({
  selector: 'app-deposit-generate',
  templateUrl: './deposit-generate.component.html',
  styleUrls: ['./deposit-generate.component.css']
})
export class DepositGenerateComponent implements OnInit {

  @Output()
  submitEmitter = new EventEmitter<boolean>();

  constructor(
    private globalStorage: GlobalStorageService,
    private accountHandler: AccountHandlerService,
    private transactionHandler: TransactionHandlerService
  ) { }

  ngOnInit(): void {
  }

  // creates a deposit request and then updates both the account and creates and an entity in the database
  onClickSubmit(amount:string, type:string, reference:string){
    let deposit: DepositRequest = new DepositRequest(type, this.globalStorage.activeAccount.accountId, reference, Number.parseFloat(amount));
    this.transactionHandler.createDeposit(deposit).subscribe(this.createDepositObserver);
  }

  createDepositObserver = {
    next: (data: PostDeposit) => {
      let activeAccount = this.globalStorage.getActiveAccount();
      activeAccount.availableBalance += data.createdObject[0].amount;
      activeAccount.pendingBalance += data.createdObject[0].amount;
      this.accountHandler.updateAccount(activeAccount).subscribe(this.updateAccountObserver);
    },
    error: (err: Error) => console.error("Create Deposit Observer Error: " + err),
    complete: () => console.log("Successfully created deposit")
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
