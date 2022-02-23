import { Location } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed, async, fakeAsync, tick } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { findIndex, Observable, of } from 'rxjs';
import { LoginCredential } from 'src/app/models/login/login-credential.model';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { NewUser } from 'src/app/models/users/new-user.model';
import { Profile } from 'src/app/models/users/profile.model';
import { PostProfile } from 'src/app/models/users/responses/post-profile';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';

import { RegisterComponent } from './register.component';

class MockUserHandler extends UserHandlerService{
  public override createProfile(userId: number, newUser: NewUser): Observable<PostProfile> {
      let postProfile: PostProfile = {
        success: true,
        createdObject: [new Profile(1, userId, newUser.firstName, newUser.lastName, newUser.email, `${newUser.phoneNumber}`, newUser.address)]
      };
      return of(postProfile);
  }
}

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let router: Router;
  let location: Location;
  let el: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule, ReactiveFormsModule]
    })
    .compileComponents();

    router = TestBed.get(Router);
    location = TestBed.get(Location);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    router.initialNavigation();
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call the onSubmit method', async (() => {
    fixture.detectChanges();
    spyOn(component, 'onSubmit');
    el = fixture.debugElement.query(By.css('button')).nativeElement;
    el.click();
    expect(component.onSubmit).toHaveBeenCalledTimes(1);
  }));

  it('should navigate to "" redirects to /login', fakeAsync(() => {
    component.goToLogin();
    router.navigate(['']);
    tick();
    expect(location.path()).toBe('/')
  }));

  it('should on submit register a new user and update the backend then return to login', () =>{
    const fixture = TestBed.createComponent(RegisterComponent);
    const app = fixture.componentInstance;

    let newUser: NewUser = new NewUser("username", "firstName", "lastName", "email@email.com", "123 address", 1231231234, "password");

    let newProfile: Profile = new Profile(1, 1, "firstName", "lastName","email@email.com", "1231231234", "123 address");

    let postLogin: PostLogin = {
      success: true,
      createdObject: [
        new LoginCredential(1, "username", "password")
      ]
    };

    let postProfile: PostProfile = {
      success: true,
      createdObject: [
        newProfile
      ]
    }

    let userHandler = fixture.debugElement.injector.get(UserHandlerService);
    let routingAllocator = fixture.debugElement.injector.get(RoutingAllocatorService);

    let loginSpy = spyOn(routingAllocator, "login").and.stub();

    let createNewLoginSpy = spyOn(userHandler, "createNewLogin").and.returnValue(of(postLogin));
    let createProfileSpy = spyOn(userHandler, "createProfile").and.returnValue(of(postProfile));

    app.newUser = newUser;
    app.registerUser();
    expect(createNewLoginSpy).toHaveBeenCalled();
    expect(createProfileSpy).toHaveBeenCalled();
    expect(loginSpy).toHaveBeenCalled();
  });

})





