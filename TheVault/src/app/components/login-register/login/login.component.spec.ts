import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
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
import { GetUser } from 'src/app/models/users/responses/get-user';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
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

  it('set submit to true', async(() => {
    component.onSubmit();
    expect(component.submitted).toBeTruthy();

  }))

  it('should on submit validate the user login and setup userId, username, and profile from s', () => {
    const fixture = TestBed.createComponent(LoginComponent);
    const app = fixture.componentInstance;

    let loginUser: LoginUser = new LoginUser("username", "password");
    let postLogin: PostLogin = {
      success: true,
      createdObject: [
        new LoginCredential(1, "username", "password")
      ]
    };

    let getUser: GetUser = {
      success: true,
      gotObject: [
        new Profile(1, 1, "firstName", "lastName", "email@email.com", "1231231234", "123 address")
      ]
    };

    let getAccounts: GetAccount = {
      success: true,
      gotObject: [
        new Account(1, 1, "Checking", 123, 123)
      ]
    };

    let userHandler = fixture.debugElement.injector.get(UserHandlerService);
    let validateLoginSpy = spyOn(userHandler, "validateLogin").and.returnValue(of(postLogin));
    let getUserProfileSpy = spyOn(userHandler, "getUserProfile").and.returnValue(of(getUser));

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let setUserIdSpy = spyOn(globalStorage, "setUserId").and.stub();
    let setUsernameSpy = spyOn(globalStorage, "setUsername").and.stub();
    let setProfileSpy = spyOn(globalStorage, 'setProfile').and.stub();
    let getUserIdSpy = spyOn(globalStorage, 'getUserId').and.returnValue(1);
    let setAcountsSpy = spyOn(globalStorage, 'setAccounts').and.stub();

    let accountHandler = fixture.debugElement.injector.get(AccountHandlerService);
    let getAccountsSpy = spyOn(accountHandler, 'getAccounts').and.returnValue(of(getAccounts));

    let router = fixture.debugElement.injector.get(RoutingAllocatorService);
    let accountViewSpy = spyOn(router, "accountView").and.stub();

    app.getUserInfo(loginUser);

    expect(validateLoginSpy).toHaveBeenCalled();
    expect(getUserProfileSpy).toHaveBeenCalled();
    expect(setUserIdSpy).toHaveBeenCalled();
    expect(setUsernameSpy).toHaveBeenCalled();
  
    expect(getUserProfileSpy).toHaveBeenCalled();
    expect(getUserIdSpy).toHaveBeenCalled();
    expect(setProfileSpy).toHaveBeenCalled();
    expect(getAccountsSpy).toHaveBeenCalled();

    expect(setAcountsSpy).toHaveBeenCalled();
    expect(accountViewSpy).toHaveBeenCalled();
  })

});
