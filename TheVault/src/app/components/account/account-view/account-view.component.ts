import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/models/account/account.model';
import { Profile } from 'src/app/models/users/profile.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

@Component({
  selector: 'app-account-view',
  templateUrl: './account-view.component.html',
  styleUrls: ['./account-view.component.css']
})
export class AccountViewComponent implements OnInit {

  userId!:number;
  profile!: Profile;
  accounts!: Account[];

  constructor(
    private globalStorage: GlobalStorageService
    ) { }

  ngOnInit(): void {
    this.initializeView();
  }

  //Using global storage we populate the account view. This acts as a way to traverse through
  //the other views
  initializeView(){
    try{
      this.userId = this.globalStorage.getUserId();
      this.profile = this.globalStorage.getProfile();
      this.accounts = this.globalStorage.getAccounts();
    }catch(err){
      console.error(err);
    }
  }

  createAccountEvent(submit:boolean){
    console.log(submit)
    if(submit)this.accounts = this.globalStorage.getAccounts();
  }


}
