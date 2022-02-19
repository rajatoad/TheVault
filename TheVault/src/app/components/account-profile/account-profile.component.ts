import { Component, Input, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/users/profile.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';

@Component({
  selector: 'app-account-profile',
  templateUrl: './account-profile.component.html',
  styleUrls: ['./account-profile.component.css']
})
export class AccountProfileComponent implements OnInit {

  @Input()
  profile!:Profile;

  constructor(
    private router: RoutingAllocatorService
  ) { }

  ngOnInit(): void {
  }

  editProfile(){
    this.router.editProfile();
  }

}
