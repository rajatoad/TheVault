import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { LoginUser } from 'src/app/models/users/login-user.model';
import { GetUser } from 'src/app/models/users/responses/get-user';
import BuildUser from 'src/app/utils/build-user';
import { AccountService } from 'src/app/_services/account/account.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { AuthService } from 'src/app/_services/auth/auth.service';
import { ProfileService } from 'src/app/_services/profile.service';
import { UserSessionService } from 'src/app/_services/user/user-session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),   
    password: new FormControl(''),
  });
  submitted = false;
  errorMessage = '';
  isLoginFailed = false;
  posts : any;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private userStorage: UserSessionService,
    private router: RoutingAllocatorService,
    private profileService: ProfileService,
    private accountService: AccountService
    ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {   
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(20)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(30)
          ]
        ],      
      },
    );
  }


get f(): { [key: string]: AbstractControl } {
  return this.form.controls;
}

onSubmit(): void {
  this.submitted = true;

  if (this.form.invalid) {
    return;
  }

  let userN = this.form.get('username')?.value
  let passW = this.form.get('password')?.value
  if(userN != null && passW != null) {
    let loginUser = new LoginUser(userN, passW);
    this.authService.validateLogin(loginUser)
      .subscribe(
        (data: PostLogin) => {
          console.log(data);
          this.userStorage.saveUserId(data.createdObject[0].userId);
          this.profileService.getUserProfile(this.userStorage.getUserId()).subscribe(
            (data: GetUser) => {
              this.userStorage.saveUser(data.gotObject[0]);
              this.accountService.getUserAccounts(data.gotObject[0].userId);
            }            
            );
          }
        );
 
  }
}

onReset(): void {
  this.submitted = false;
  this.form.reset();
}
}
