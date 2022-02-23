import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { LoginUser } from 'src/app/models/users/login-user.model';
import { GetUser } from 'src/app/models/users/responses/get-user';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';

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
    private userHandler: UserHandlerService,
    private globalStorage: GlobalStorageService,
    private accountHandler: AccountHandlerService,
    private router: RoutingAllocatorService
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


  /* istanbul ignore next */
get f(): { [key: string]: AbstractControl } {
  return this.form.controls;
}

onSubmit(): void {
  this.submitted = true;

  if (this.form.invalid) {
    return;
  }

  /* istanbul ignore next */
  let userN = this.form.get('username')?.value
  /* istanbul ignore next */
  let passW = this.form.get('password')?.value
  /* istanbul ignore next */
  if(userN != null && passW != null) {
    let loginUser = new LoginUser(userN, passW);
    this.getUserInfo(loginUser);
  }
}

//Validate a users input, returning a response on a successful login
getUserInfo(loginUser: LoginUser){
  this.userHandler.validateLogin(loginUser).subscribe(this.loginObserver);
}

// On a successful login, the profile is requested, and on that success the user accounts are retrieved.
// After getting all accounts, we move to the next view for account view.
loginObserver = {
  next: (data: PostLogin) => {
    this.globalStorage.setUserId(data.createdObject[0].userId);
    this.globalStorage.setUsername(data.createdObject[0].username);
    this.userHandler.getUserProfile(this.globalStorage.getUserId()).subscribe(this.profileObserver)
  },
  error: (err: Error) => {
    console.error("Login Observer error: " + err);
    this.onReset();},
  complete: () => console.log("Completed getting login")
}

profileObserver = {
  next: (data: GetUser) => {
    this.globalStorage.setProfile(data.gotObject[0]);
    this.accountHandler.getAccounts(this.globalStorage.getUserId()).subscribe(this.accountObserver);
  },
  error: (err: Error) => {
  /* istanbul ignore next */
    console.error("profile observer error: " + err);
  /* istanbul ignore next */
    this.onReset();},
  complete: () => console.log("Completed getting profile")
}

accountObserver = {
  next: (data: GetAccount) => {
    this.globalStorage.setAccounts(data.gotObject);
    this.router.accountView();
  },
  /* istanbul ignore next */
  error: (err: Error) => console.log("account observer error: " + err),
  complete: () => console.log("Completed getting user accounts")
}

  /* istanbul ignore next */
onReset(): void {
  this.submitted = false;
  this.form.reset();
}
}
