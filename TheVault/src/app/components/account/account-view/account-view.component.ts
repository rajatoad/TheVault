import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Profile } from 'src/app/models/users/profile.model';
import { AccountService } from 'src/app/_services/account/account.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';
import { UserService } from 'src/app/_services/user/user.service';

@Component({
  selector: 'app-account-view',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit {

  userId!:number;

  profile!: Profile;

  name!:string;

  constructor(
    private accountService: AccountService,
    private userService: UserService,
    private userSession: UserSessionService) { }

    ngOnInit(): void {
      this.initializeView();
    }

    initializeView(){
      this.userId = this.userSession.getUserId();
      this.profile = this.userSession.getProfile();
    }

}
