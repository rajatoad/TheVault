import { TransferMoney } from './../../../models/transaction/responses/transfer';
import { TransferGenerateService } from './../../../_services/transactions/transfer-generate.service';
import { TransferRequest } from './../../../models/transaction/request/transfer-request.model';
import { AccountService } from './../../../_services/account/account.service';
import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { AccountRetrieverService } from 'src/app/_services/backend/account-retriever.service';
import { GetAccount } from 'src/app/models/account/responses/get-account';



@Component({
  selector: 'app-transfer-generate',
  templateUrl: './transfer-generate.component.html',
  styleUrls: ['./transfer-generate.component.css']
})
export class TransferGenerateComponent implements OnInit {

  // @Output()
  // submitEmitter = new EventEmitter<boolean>();
  @Input()
  userId!: number;

  constructor(
    private accountService: AccountService,
    private transferService: TransferGenerateService,
    private accountHttp: AccountRetrieverService

  ) { }

  ngOnInit(): void {
    // method for loading accounts goes here
    this.getAccounts();
  }
  userAccounts!: Account[]

  getAccounts(): void {
    this.accountHttp.getUserAccount(this.userId)
      .subscribe(
        (data: GetAccount) => this.userAccounts = data.gotObject)
  };
}


//   onClickSubmit(receiverAccount: number, amount: number) {
//     console.log(this.accountService.activeAccount)
//     let ownerAccount: number = // find the users account number.
//       let transfer: TransferRequest = new TransferRequest(ownerAccount, receiverAccount, amount)
//     console.log(transfer)
//     this.transferService.createTransfer(transfer).subscribe(
//       (data: TransferMoney) => {
//         console.log(data);
//     this.submitEmitter.emit(false);
//   }
//   )
// }




// }
