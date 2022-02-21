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
});
