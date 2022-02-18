import { Component, OnInit } from '@angular/core';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  constructor(
    private globalStorage: GlobalStorageService,
    private accountHandler: AccountHandlerService
  ) { }

  ngOnInit(): void {
  }

  //Creating an account is sent as a request to the server, on a successful return it is added to the account
  //array on client side.
  onClickSubmit(accountType:string){
    this.accountHandler.createAccount(this.globalStorage.getUserId(), accountType)
      .subscribe(this.createAccountObserver)
  }

  createAccountObserver = {
    next: (data: PostAccount) => this.globalStorage.addAccount(data.createdObject[0]),
    error: (err: Error) => console.error("Create Account Observer error: " + err),
    complete: () => console.log("Successful creation of account")
  }
}
