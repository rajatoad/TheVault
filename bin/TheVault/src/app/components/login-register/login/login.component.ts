import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginUser } from 'src/app/models/users/login-user.model';
import BuildUser from 'src/app/utils/build-user';
import { AuthService } from 'src/app/_services/auth/auth.service';
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
    private userStorage: UserSessionService 
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
    let loginUser = new LoginUser(userN, passW)
    this.authService.login(loginUser).subscribe({
      next: data => {         
        console.log(data);
        this.isLoginFailed = false;
        let builtUser = BuildUser.userBuilder(data);
        this.userStorage.saveUser(builtUser)
   
      },
      error: err => {
        this.errorMessage = err.error.message;
        console.log("Login failed")
        this.isLoginFailed = true;
      }
    });        
  }
}

onReset(): void {
  this.submitted = false;
  this.form.reset();
}
}
