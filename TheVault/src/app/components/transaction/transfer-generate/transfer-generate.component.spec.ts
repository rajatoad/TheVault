import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { Deposit } from 'src/app/models/transaction/deposit.model';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { TransferRequest } from 'src/app/models/transaction/request/transfer-request.model';
import { WithdrawRequest } from 'src/app/models/transaction/request/withdraw-request.model';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { Withdraw } from 'src/app/models/transaction/withdraw.model';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

import { TransferGenerateComponent } from './transfer-generate.component';

class MockGlobalStorage extends GlobalStorageService{
  public override getAccounts(): Account[] {
      let accounts: Account[] = [new Account(1, 1, "Checking", 111, 111), new Account(2, 1, "Savings", 11, 11)];
      return accounts;
  }

  public override getActiveAccount(): Account {
      return new Account(1, 1, "Checking", 111, 111);
  }

  public override setActiveAccount(account: Account): void {
      return;
  }
}

class MockAccountHandler extends AccountHandlerService{
  public override createTransfer(transfer: TransferRequest): Observable<PutAccount> {
      let putAccount: PutAccount = {
        success: true,
        updatedObject: [new Account(1, 1, "Checking", 110, 110), new Account(2, 1, "Savings", 12, 12)]
      };
      return of(putAccount);
  }
}

class MockTransactionHandler extends TransactionHandlerService{
  public override createWithdraw(withdraw: WithdrawRequest): Observable<PostWithdraw> {
      let postWithdraw: PostWithdraw = {
        success: true,
        createdObject: [new Withdraw(1, withdraw.accountId, "Transfer", "Pending", "reference", `${Date.now}`, 1)]
      }
      return of(postWithdraw);
  }

  public override createDeposit(deposit: DepositRequest): Observable<PostDeposit> {
      let postDeposit: PostDeposit = {
        success: true,
        createdObject: [new Deposit(1, deposit.accountId, "Transfer", "reference", `${Date.now}`, 1)]
      }
      return of(postDeposit);
  }
}

describe('TransferGenerateComponent', () => {
  let component: TransferGenerateComponent;
  let fixture: ComponentFixture<TransferGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferGenerateComponent ],
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
    fixture = TestBed.createComponent(TransferGenerateComponent);
    component = fixture.componentInstance;
    component.userId = 1;
    component.userAccounts = [new Account(1, 1, "Checking", 111, 111), new Account(2, 1, "Savings", 11, 11)];
    component.activeAccountId = 1;
    component.receiverAccount = new Account(2, 1, "Savings", 11, 11);
    component.amount = 1;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
