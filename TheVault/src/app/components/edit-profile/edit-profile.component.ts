import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/users/profile.model';
import { PutProfile } from 'src/app/models/users/responses/put-profile';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  profile:Profile | undefined;

  firstName:string = '';
  lastName:string = '';
  email:string = '';
  phoneNumber:string = '';
  address:string = '';

  constructor(
    private globalStorage: GlobalStorageService,
    private location: Location,
    private userHandler: UserHandlerService
  ) { }

  ngOnInit(): void {
    this.setupProfile();
  }

  setupProfile():void{
    this.profile = this.globalStorage.getProfile();
    try{
      this.firstName = this.profile.firstName;
      this.lastName = this.profile.lastName;
      this.email = this.profile.email;
      this.phoneNumber = this.profile.phoneNumber;
      this.address = this.profile.address;
    }catch(err){
      console.error(err);
    }
  }

  goBack():void{
    this.location.back();
  }

  submit():void{
    if(this.profile != undefined){
      this.profile.firstName = this.firstName;
      this.profile.lastName = this.lastName;
      this.profile.email = this.email;
      this.profile.phoneNumber = this.phoneNumber;
      this.profile.address = this.address;
      this.userHandler.updateProfile(this.profile, this.profile.profileId, this.globalStorage.getUserId()).subscribe(this.updateProfileObserver);
    }else return;
  }

  updateProfileObserver = {
    next: (data: PutProfile) => {
      this.globalStorage.setProfile(data.updatedObject[0]);
      this.goBack();
    }
  }
}
