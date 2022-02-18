import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUser } from '../models/users/new-user.model';
import { GetUser } from '../models/users/responses/get-user';
import { PostProfile } from '../models/users/responses/post-profile';
import { User } from '../models/users/user.model';


const AUTH_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  userProfile!: User;

  constructor(
    private http: HttpClient
  ) { }

  getUserProfile(userId:number){
    let getUserProfileUrl = `${AUTH_API}profile/get/${userId}`;
    return this.http.get<GetUser>(getUserProfileUrl);
  }

  createProfile(userId:number, newUser:NewUser){
    let newProfile = `${AUTH_API}profile/create`
    return this.http.post<PostProfile>(newProfile, JSON.stringify({
      userId:userId,
      firstName: newUser.firstName,
      lastName: newUser.lastName,
      email: newUser.email,
      phoneNumber: newUser.phoneNumber,
      address: newUser.address
    }), httpOptions);
  }
}
