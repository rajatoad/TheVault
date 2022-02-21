import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const ROUTES = {
  login: [''],
  register: ['register']
}

@Injectable({
  providedIn: 'root'
})

export class RoutingAllocatorService {

  constructor(private router: Router) { }

  login():void{this.router.navigate(ROUTES.login);}

  register():void{this.router.navigate(ROUTES.register);}
}
