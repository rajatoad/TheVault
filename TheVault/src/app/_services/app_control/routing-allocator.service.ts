import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const ROUTES = {
  login: [''],
  register: ['register'],
  select: ['account-view'],
  detail: ['account-detail'],
  profile: ['profile'],
  editProfile: ['edit-profile']
}

@Injectable({
  providedIn: 'root'
})

export class RoutingAllocatorService {

  constructor(private router: Router) { }

  login():void{this.router.navigate(ROUTES.login)};

  register():void{this.router.navigate(ROUTES.register)};

  detail():void{this.router.navigate(ROUTES.detail)};

  profile():void{this.router.navigate(ROUTES.profile)};

  accountView():void{this.router.navigate(ROUTES.select)};

  editProfile():void{this.router.navigate(ROUTES.editProfile)};
}
