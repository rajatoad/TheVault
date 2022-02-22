import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Account } from 'src/app/models/account/account.model';
import { Profile } from 'src/app/models/users/profile.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

import { AccountViewComponent } from './account-view.component';

class MockGlobalStorage extends GlobalStorageService {
  public override getUserId(): number {
      return 1;
  }
  public override getProfile(): Profile {
      return new Profile(
        1,
        1,
        "firstName",
        "lastName",
        "email@email.com",
        "1112223333",
        "123 address"
      );
  }
  public override getAccounts(): Account[] {
      let accounts: Account[] = [
        new Account(
              1,
              1,
              "Checking",
              111,
              111
        )
      ];
      return accounts;
  }
}

describe('AccountViewComponent', () => {
  let component: AccountViewComponent;
  let fixture: ComponentFixture<AccountViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountViewComponent ],
      imports: [],
      providers: [
        {
          provider: GlobalStorageService,
          useClass: MockGlobalStorage
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountViewComponent);
    component = fixture.componentInstance;

    component.profile = new Profile(1, 1, "name", "name", "email@email.com", "1231231234", "123 address");
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the view with userId, profile and accounts', () => {
    const fixture = TestBed.createComponent(AccountViewComponent);
    const app = fixture.componentInstance;

    let profile:Profile = new Profile(1, 1, "name", "name", "email", "123","123");
    let account: Account = new Account(1, 1, "type", 1, 1);

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let globalUserIdSpy = spyOn(globalStorage, 'getUserId').and.returnValue(1);
    let globalProfileSpy = spyOn(globalStorage, 'getProfile').and.returnValue(profile);
    let globalAccountSpy = spyOn(globalStorage, 'getAccounts').and.returnValue([account]);

    app.initializeView();
    expect(globalUserIdSpy).toHaveBeenCalled();
    expect(app.userId).toBe(1);
    expect(globalProfileSpy).toHaveBeenCalled();
    expect(app.profile).toBe(profile);
    expect(globalAccountSpy).toHaveBeenCalled();
    expect(app.accounts.length).toBe(1);
    expect(app.accounts[0]).toBe(account);
     
  })

  it('should on createAccount update the accounts from the global storage', () => {
    const fixture = TestBed.createComponent(AccountViewComponent);
    const app = fixture.componentInstance;

    let account: Account = new Account(1, 1, "type", 1, 1);
    let account2: Account = new Account(2, 1, "type2", 3, 3);

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let globalGetAccountSpy = spyOn(globalStorage, 'getAccounts').and.returnValue([account, account2]);

    app.createAccountEvent(true);
    expect(globalGetAccountSpy).toHaveBeenCalled();
    expect(app.accounts.length).toBeGreaterThan(1);
    expect(app.accounts[1]).toBe(account2);
  })

  it('should not update the accounts on an unsuccessful creation of account', () => {
    const fixture = TestBed.createComponent(AccountViewComponent);
    const app = fixture.componentInstance;
    
    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let globalGetAccountSpy = spyOn(globalStorage, 'getAccounts').and.stub();

    app.createAccountEvent(false);
    expect(globalGetAccountSpy).not.toHaveBeenCalled();
  })
});
