import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { GetAccount } from 'src/app/models/account/responses/get-account';
import { DeleteDeposit } from 'src/app/models/transaction/responses/delete-deposit';
import { DeleteWithdraw } from 'src/app/models/transaction/responses/delete-withdraw';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

import { SelectComponent } from './select.component';

class MockGlobalStorage extends GlobalStorageService {
    public override getUserId(): number {
        return 1;
    }
}

class MockAccountHandler extends AccountHandlerService{
  public override getAccounts(userId: number): Observable<GetAccount> {
      let getAccounts: GetAccount = {
        success: true,
        gotObject: [
          new Account(1, userId, "Checking", 100, 100)
        ]
      }
      return of(getAccounts)
  }
}

class MockTransactionHandler extends TransactionHandlerService{
  public override deleteAllDeposits(accountId: number): Observable<DeleteDeposit> {
      return of();
  }

  public override deleteAllWithdraws(accountId: number): Observable<DeleteWithdraw> {
      return of();      
  }
}

describe('SelectComponent', () => {
  let component: SelectComponent;
  let fixture: ComponentFixture<SelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectComponent ],
      imports: [HttpClientTestingModule],
      providers: [
        {
          providers: GlobalStorageService,
          useClass: MockGlobalStorage
        },
        {
          providers: AccountHandlerService,
          useClass: MockAccountHandler
        },
        {
          providers: TransactionHandlerService,
          useClass: MockTransactionHandler
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
