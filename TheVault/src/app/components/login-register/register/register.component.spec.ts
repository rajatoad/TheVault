import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable, of } from 'rxjs';
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

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule, ReactiveFormsModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
