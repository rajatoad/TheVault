import { TransferMoney } from './../../../models/transaction/responses/transfer';
import { TransferGenerateService } from './../../../_services/transactions/transfer-generate.service';
import { TransferRequest } from './../../../models/transaction/request/transfer-request.model';
import { AccountService } from './../../../_services/account/account.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountRetrieverService } from 'src/app/_services/backend/account-retriever.service';
import { GetAccount } from 'src/app/models/account/responses/get-account';

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

  constructor(
    private accountService: AccountService,
    private transferService: TransferGenerateService,
    private accountHttp: AccountRetrieverService
  ) { }

  ngOnInit(): void {
    this.getAccounts();
  }
  userAccounts!: Account[]
  user: any | undefined

  getAccounts(): void {
    this.accountHttp.getUserAccount(this.userId)
      .subscribe(
        (data: GetAccount) =>
          this.userAccounts = data.gotObject
      )
  };


  onClickSubmit(ownerAccount: string, receiverAccount: string, amount: string) {
    console.log(this.accountService.activeAccount)
    let transfer: TransferRequest = new TransferRequest(+ownerAccount, +receiverAccount, Number.parseFloat(amount))
    console.log(transfer)
    this.transferService.createTransfer(transfer).subscribe(
      (data: TransferMoney) => {
        console.log(data);
        this.submitEmitter.emit(false);
      }
    )
  }


}
