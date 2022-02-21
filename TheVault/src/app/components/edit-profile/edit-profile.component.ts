import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/users/profile.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

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
    private location: Location
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
      this.globalStorage.setProfile(this.profile);
    }else return;
  }
}
