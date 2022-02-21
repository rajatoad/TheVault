import { TransferRequest } from './../../../models/transaction/request/transfer-request.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { PutAccount } from 'src/app/models/account/responses/put-account';

@Component({
  selector: 'app-transfer-generate',
  templateUrl: './transfer-generate.component.html',
  styleUrls: ['./transfer-generate.component.css']
})

export class TransferGenerateComponent implements OnInit {

  @Output()
  submitEmitter = new EventEmitter<boolean>();

  @Input()
  userId!: number;

  userAccounts: Account[] = [];

  activeAccountId!: number;

  receiverAccount!: Account;

  amount!: number;

  constructor(
    private globalStorage: GlobalStorageService,
    private transactionHandler: TransactionHandlerService,
    private accountHandler: AccountHandlerService
  ) { }

  ngOnInit(): void {
    this.getAccounts();
  }

  getAccounts(): void {
    try{
      this.userAccounts = this.globalStorage.getAccounts();
      this.activeAccountId = this.globalStorage.getActiveAccount().accountId;
    }catch(err){
      console.error(err);
    }
  };

  selectReceiver(receiverAccount: Account){
    this.receiverAccount = receiverAccount;
  }

  onClickSubmit(amount: string) {
    this.amount = Number.parseFloat(amount);

    if(Number.parseFloat(amount) > this.globalStorage.getActiveAccount().availableBalance) {
      window.alert("TOO MUCH MONEY BUDDY");
      this.submitEmitter.emit(false);
      return;
    }

    let withdraw: WithdrawRequest = new WithdrawRequest(
      this.activeAccountId, 
      'Transfer', `Transfer to Account: ${this.receiverAccount.accountId}`,
      Number.parseFloat(amount)
    );
    
    this.transactionHandler.createWithdraw(withdraw).subscribe(this.createWithdrawObserver);
  }


  createWithdrawObserver = {
    next: (data:PostWithdraw) => {
      let activeAccount = this.globalStorage.getActiveAccount();
      activeAccount.availableBalance -= data.createdObject[0].amount;
      activeAccount.pendingBalance -= data.createdObject[0].amount;
      let deposit: DepositRequest = new DepositRequest(
        'Transfer', 
        this.receiverAccount.accountId,
        `Transer from Account: ${this.activeAccountId}`,
        this.amount
      )
      this.transactionHandler.createDeposit(deposit).subscribe(this.createDepositObserver);
    },
    error: (err: Error) => console.error("Create Withdraw Observer Error: " + err),
    complete: () => console.log("Successful creation of withdraw")
  }

  createDepositObserver = {
    next: (data: PostDeposit) => {
      let activeAccount = this.globalStorage.getActiveAccount();
      activeAccount.availableBalance += data.createdObject[0].amount;
      activeAccount.pendingBalance += data.createdObject[0].amount;
      let transfer: TransferRequest = new TransferRequest(
        this.activeAccountId, 
        this.receiverAccount.accountId, 
        this.amount
      );
      this.accountHandler.createTransfer(transfer).subscribe(this.createTransferObserver);
    },
    error: (err: Error) => console.error("Create Deposit Observer Error: " + err),
    complete: () => console.log("Successfully created deposit")
  }

  createTransferObserver = {
    next: (data: PutAccount) => {
      this.globalStorage.setActiveAccount(data.updatedObject[0]);
      this.submitEmitter.emit(false);
    }, 
    error: (err: Error) => console.error("Failed to make transfer: " + err),
    complete: () => console.log("Successfully made transfer")
  }

}
