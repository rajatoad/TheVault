import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { PostAccount } from 'src/app/models/account/responses/post-account';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';

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
});
