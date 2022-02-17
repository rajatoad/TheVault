import { Component, OnInit } from '@angular/core';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { AccountService } from 'src/app/_services/account/account.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  constructor(
    private accountService: AccountService,
    private userService: UserSessionService
  ) { }

  ngOnInit(): void {
  }

  onClickSubmit(accountType:string){
    this.accountService.createAccount(this.userService.getUserId(), accountType).subscribe(
      (data: PostAccount) => {
        this.accountService.addAccount(data.createdObject[0]);
      }
    )
  }
}
