import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { LoginCredential } from 'src/app/models/login/login-credential.model';
import { PostLogin } from 'src/app/models/login/responses/post-login';
import { LoginUser } from 'src/app/models/users/login-user.model';
import { Profile } from 'src/app/models/users/profile.model';
import { GetProfile } from 'src/app/models/users/responses/get-profile';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';

import { LoginComponent } from './login.component';

class MockGlobalStorage extends GlobalStorageService{
  public override setUserId(userId: number): void {
      return;
  };

  public override getUserId(): number {
      return 1;
  }

  public override setUsername(username: string): void {
      return;
  }

  public override setProfile(user: Profile): void {
      return;
  };

  public override setAccounts(accounts: Account[]): void {
      return;
  }
}

class MockUserHandler extends UserHandlerService{
  public override validateLogin(login: LoginUser): Observable<PostLogin> {
      let postLogin: PostLogin = {
        success: true,
        createdObject: [new LoginCredential(1, login.username, login.password)]
      };
      return of(postLogin);
  };

  public override getUserProfile(userId: number): Observable<GetProfile> {
      let getProfile: GetProfile = {
        success: true,
        gotObject: [new Profile(1, userId, "name", "name", "email@email.com", "1231231234", "123 Address")]
      };
      return of(getProfile);
  }
}

class MockAccountHandler extends AccountHandlerService{
  public override getAccounts(userId: number): Observable<GetAccount> {
      let getAccount:GetAccount = {
        success: true,
        gotObject: [new Account(1, userId, "Checking", 111, 111)]
      };
      return of(getAccount);
  }
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule, ReactiveFormsModule],
      providers: [
        {
          providers: UserHandlerService,
          useClass: MockUserHandler
        },
        {
          providers: GlobalStorageService,
          useClass: MockGlobalStorage
        },
        {
          providers: AccountHandlerService,
          useClass: MockAccountHandler
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
