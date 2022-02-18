import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { RoutingAllocatorService } from '../app_control/routing-allocator.service';
import { ProfileService } from '../profile.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private router: RoutingAllocatorService,
    private profileService: ProfileService
  ) { }

  createNewLogin(username:string, password:string){
    let createLoginUrl = `http://localhost:8080/login/create`;
    return this.http.post<PostLogin>(createLoginUrl, JSON.stringify({
      username:username,
      password:password
    }), httpOptions);
  }

}
