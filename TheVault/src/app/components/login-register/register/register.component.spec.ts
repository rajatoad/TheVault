import { Location } from '@angular/common';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed, async, fakeAsync } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { findIndex, Observable, of } from 'rxjs';
import { NewUser } from 'src/app/models/users/new-user.model';
import { Profile } from 'src/app/models/users/profile.model';
import { PostProfile } from 'src/app/models/users/responses/post-profile';
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

  it('navigate to "" redirects you to login', fakeAsync(() => {
    component.goToLogin();
    router.navigate(['']);
      expect(location.path()).toBe('');
    })
  );
  
  it('set submit to true', async(() => {
    component.onSubmit();
    expect(component.submitted).toBeTruthy();
}))});




