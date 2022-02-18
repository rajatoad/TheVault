import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { NewUser } from 'src/app/models/users/new-user.model';
import { PostProfile } from 'src/app/models/users/responses/post-profile';
import Validation from 'src/app/utils/validation';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });
  submitted = false;
  posts: any;

  newUser!: NewUser;

  constructor(
    private formBuilder: FormBuilder,
    private routingAllocator: RoutingAllocatorService,
    private userHandler: UserHandlerService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm():void{
    this.form = this.formBuilder.group(
      {
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        firstName: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(25)
          ]
        ],
        lastName: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(25)
          ]
        ],
        email: [
          '',
          [
            Validators.required,
            Validators.email,
            Validators.maxLength(50)
          ]
        ],
        address: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(50)
          ]
        ],
        phoneNumber: [
          '',
          [
            Validators.required,
            Validators.minLength(1),
            Validators.maxLength(25)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
        confirmPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(25)
          ]
        ],
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  goToLogin(): void {
    this.routingAllocator.login();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }
    let userN = this.form.get('username')?.value;
    let firstN = this.form.get('firstName')?.value;
    let lastN = this.form.get('lastName')?.value;
    let email = this.form.get('email')?.value;
    let addr = this.form.get('address')?.value;
    let phoneN = this.form.get('phoneNumber')?.value;
    let passW = this.form.get('password')?.value;
    if (userN != null && firstN != null && lastN != null && email != null && addr != null 
       && phoneN != null && passW != null) {
         
      this.newUser = new NewUser(userN, firstN, lastN, email, addr, phoneN, passW);
      this.registerUser(this.newUser);
    }
  }

      // Creates a new user login and a new profile using two separate endpoints
  registerUser(newUser: NewUser){
    this.userHandler.createNewLogin(newUser.username, newUser.password).subscribe(this.loginObserver)
  }

  // On a successful login credential creation, a new profile is made and returned back to login
  loginObserver = {
    next: (data: PostLogin) => {
      this.userHandler.createProfile(data.createdObject[0].userId, this.newUser).subscribe(this.profileObserver)
    },
    error: (err: Error) => {
      console.error("Login Observer error: " + err);
      this.onReset();},
    complete: () => console.log("Completed creating login credentials")
  }

  profileObserver = {
    next: (data: PostProfile) => this.goToLogin(),
    error: (err: Error) => {
      console.error("Profile Observer error: " + err);
      this.onReset();},
    complete: () => console.log("Completed creating user profile")
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

}
