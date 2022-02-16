import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GetUser } from '../models/users/responses/get-user';
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
}
