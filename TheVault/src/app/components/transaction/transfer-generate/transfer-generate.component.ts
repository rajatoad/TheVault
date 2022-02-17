import { TransferMoney } from './../../../models/transaction/responses/transfer';
import { TransferGenerateService } from './../../../_services/transactions/transfer-generate.service';
import { TransferRequest } from './../../../models/transaction/request/transfer-request.model';
import { AccountService } from './../../../_services/account/account.service';
import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';



@Component({
  selector: 'app-transfer-generate',
  templateUrl: './transfer-generate.component.html',
  styleUrls: ['./transfer-generate.component.css']
})
export class TransferGenerateComponent implements OnInit {

  // @Output()
  // submitEmitter = new EventEmitter<boolean>();


  constructor(
    private accountService: AccountService,
    private transferService: TransferGenerateService
  ) { }

  ngOnInit(): void {
    // method for loading accounts goes here
  }
  userAccounts: Account[] = []

  onClickSubmit(//parameters) {
    console.log(this.accountService.activeAccount)
let transfer: TransferRequest = new TransferRequest(//parameters)
  console.log(transfer)
    this.transferService.createTransfer(transfer).subscribe(
    (data: TransferMoney) => {
      console.log(data);
      this.submitEmitter.emit(false);
    }
  )
  }




}
