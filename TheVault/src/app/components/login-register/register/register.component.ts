import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { NewUser } from 'src/app/models/users/new-user.model';
import { PostProfile } from 'src/app/models/users/responses/post-profile';
import Validation from 'src/app/utils/validation';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { AuthService } from 'src/app/_services/auth/auth.service';
import { ProfileService } from 'src/app/_services/profile.service';
import { UserService } from 'src/app/_services/user/user.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private routingAllocator: RoutingAllocatorService,
    private userService: UserService,
    private profileService: ProfileService
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
         
      let newUser = new NewUser(userN, firstN, lastN, email, addr, phoneN, passW);

      this.userService.createNewLogin(userN, passW).subscribe(
        (data: PostLogin) => {
          this.profileService.createProfile(data.createdObject[0].userId, newUser).subscribe(
            (data: PostProfile) => {
              console.log(data);
              this.goToLogin();
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
