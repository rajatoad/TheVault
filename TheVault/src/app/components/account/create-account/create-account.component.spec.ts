import { HttpClientTestingModule } from '@angular/common/http/testing';
import { error } from '@angular/compiler/src/util';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

import { CreateAccountComponent } from './create-account.component';

class MockAccountHandler extends AccountHandlerService{
  public override createAccount(userId: number, accountType: string): Observable<PostAccount> {
    let postAccount: PostAccount = {
      success: true,
      createdObject: [new Account(1, userId, accountType, 0, 0)]
    }
    return of(postAccount);
  }
}

describe('CreateAccountComponent', () => {
  let component: CreateAccountComponent;
  let fixture: ComponentFixture<CreateAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateAccountComponent ],
      imports: [HttpClientTestingModule],
      providers: [
        {
          provider: AccountHandlerService,
          useClass: MockAccountHandler
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should on click submit call the create account method from accountHandler', () => {
    const fixture = TestBed.createComponent(CreateAccountComponent);
    const app = fixture.componentInstance;

    let userId = 1;
    let accountType = "Checking";

    let postAccount: PostAccount = {
      success: true,
      createdObject: [new Account(1, userId, accountType, 0, 0)]
    }

    let accountHandler = fixture.debugElement.injector.get(AccountHandlerService);
    let createAccountSpy = spyOn(accountHandler, 'createAccount').and.returnValue(of(postAccount));
    
    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let addAccountSpy = spyOn(globalStorage, 'addAccount').and.stub();


    app.onClickSubmit("Checking");
    expect(createAccountSpy).toHaveBeenCalled();
    expect(addAccountSpy).toHaveBeenCalled();
  })

});
