import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/';

const ENDPOINTS = {
  LOGIN: `${AUTH_API}login`,
  REGISTER: `${AUTH_API}login/register`
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
    //return this.http.post(AUTH_API, JSON.stringify({
    return this.http.post(ENDPOINTS.REGISTER, JSON.stringify(data), httpOptions);
  }
}
