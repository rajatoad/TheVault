import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/users/profile.model';
import { UserSessionService } from 'src/app/_services/user/user-session.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  profile!:Profile;

  firstName:string = '';
  lastName:string = '';
  email:string = '';
  phoneNumber:string = '';
  address:string = '';

  constructor(
    private userSession: UserSessionService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.setupProfile();
  }

  setupProfile():void{
    this.profile = this.userSession.getProfile();
    this.firstName = this.profile.firstName;
    this.lastName = this.profile.lastName;
    this.email = this.profile.email;
    this.phoneNumber = this.profile.phoneNumber;
    this.address = this.profile.address;
  }

  goBack():void{
    this.location.back();
  }

  submit():void{
    this.profile.firstName = this.firstName;
    this.profile.lastName = this.lastName;
    this.profile.email = this.email;
    this.profile.phoneNumber = this.phoneNumber;
    this.profile.address = this.address;
    console.log(this.profile);
    this.userSession.saveUser(this.profile);
  }
}
