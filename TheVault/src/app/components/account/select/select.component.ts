import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { DeleteAccount } from 'src/app/models/account/responses/delete-account';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { DeleteDeposit } from 'src/app/models/transaction/responses/delete-deposit';
import { DeleteWithdraw } from 'src/app/models/transaction/responses/delete-withdraw';
import { AccountService } from 'src/app/_services/account/account.service';
import { AccountRetrieverService } from 'src/app/_services/backend/account-retriever.service';
import { DepositGenerateService } from 'src/app/_services/transactions/deposit-generate.service';
import { WithdrawGenerateService } from 'src/app/_services/transactions/withdraw-generate.service';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  @Input()
  userId!:number;

  @Input()
  accounts!: Account[];

  constructor(
    private accountService: AccountService,
    private accountHttp: AccountRetrieverService,
    private depositService: DepositGenerateService,
    private withdrawService: WithdrawGenerateService
  ) { }


  ngOnInit(): void {
    this.setupAccounts();
  }

  setupAccounts(){
    this.accounts = this.accountService.getStoredAccounts();
  }

  deleteAccount(account:Account, index:number){
    this.depositService.deleteAllDeposits(account.accountId)
    .subscribe(
      (data: DeleteDeposit) => {
        this.withdrawService.deleteAllWithdraws(account.accountId)
        .subscribe(
          (data: DeleteWithdraw) => {
            this.accountService.deleteAccount(account)
            .subscribe(
            (data: DeleteAccount) => {
              window.alert(`${data.deletedObject[0].accountId} GOODBYE`); 
              this.accounts.splice(index, 1);
            } 
            )
          }
        )
      }
    )


  }

}
