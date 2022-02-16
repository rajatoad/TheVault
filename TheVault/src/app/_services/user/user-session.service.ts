import { Injectable } from '@angular/core';
import { Profile } from 'src/app/models/users/profile.model';
import { User } from 'src/app/models/users/user.model';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class UserSessionService {

  userId!: number;
  userProfile!: Profile;

  constructor() { }

  public saveUser(user: Profile): void {
    this.userProfile = user;
  }

  public getUser(): Profile {
    return this.userProfile;
  }

  saveUserId(userId: number){
    this.userId = userId;
  }

  getUserId():number{
    return this.userId;
  }
}
