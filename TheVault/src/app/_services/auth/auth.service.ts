import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginUser } from 'src/app/models/users/login-user.model';
import { PostLogin } from 'src/app/models/login/responses/post-login';

const AUTH_API = 'http://localhost:8080/';

const ENDPOINTS = {
  LOGIN: `${AUTH_API}login`,
  REGISTER: `${AUTH_API}login/register`,
  VALIDATE: `${AUTH_API}login/validate`
}

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http: HttpClient) { }

  login(data: any): Observable<any> {
    return this.http.post(ENDPOINTS.LOGIN,  JSON.stringify(data), httpOptions);  
  }

  register(data: any): Observable<any> {
    console.log(data)
    return this.http.post(ENDPOINTS.REGISTER, JSON.stringify(data), httpOptions);
  }

  validateLogin(login:LoginUser){
    let validateLoginUrl = `${ENDPOINTS.VALIDATE}`;
    console.log(login);
    return this.http.post<PostLogin>(validateLoginUrl, JSON.stringify(
      {
        username: login.username,
        password: login.password
      }
    ), httpOptions);
  }
}
