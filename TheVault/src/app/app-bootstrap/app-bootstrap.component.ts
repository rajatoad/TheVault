import { Component, OnInit } from '@angular/core';
import { Account } from '../models/account/account.model';
@Component({
  selector: 'app-app-bootstrap',
  templateUrl: './app-bootstrap.component.html',
  styleUrls: ['./app-bootstrap.component.css']
})
export class AppBootstrapComponent implements OnInit {
  active?: boolean;

  constructor() { this.active =true}

  ngOnInit(): void {
  }

}
