import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { PostWithdraw } from 'src/app/models/transaction/responses/post-withdraw';
import { Withdraw } from 'src/app/models/transaction/withdraw.model';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

import { WithdrawGenerateComponent } from './withdraw-generate.component';

class MockGlobalStorage extends GlobalStorageService{

}

class MockAccountHandler extends AccountHandlerService{

}

class MockTransactionHandler extends TransactionHandlerService{

}
describe('WithdrawGenerateComponent', () => {
  let component: WithdrawGenerateComponent;
  let fixture: ComponentFixture<WithdrawGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WithdrawGenerateComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WithdrawGenerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create withdraw in server on successful withdraw request', () => {
    const fixture = TestBed.createComponent(WithdrawGenerateComponent);
    const app = fixture.componentInstance;

    let account: Account = new Account(1 ,1, "Checking", 123, 123);
    let newWithdraw: Withdraw = new Withdraw(1, 1, "Payment", "Pending", "reference", (Date.now).toString(), 1);

    let postWithdraw: PostWithdraw = {
      success: true,
      createdObject: [newWithdraw]
    };

    let updatedAccount: Account = new Account(1, 1, "Checking", 122, 122);
    let putAccount: PutAccount = {
      success: true,
      updatedObject: [updatedAccount]
    };

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let getActiveAccountSpy = spyOn(globalStorage, "getActiveAccount").and.returnValue(account);

    let transactionHandler = fixture.debugElement.injector.get(TransactionHandlerService);
    let createWithdrawSpy = spyOn(transactionHandler, "createWithdraw").and.returnValue(of(postWithdraw));

    let accountHandler = fixture.debugElement.injector.get(AccountHandlerService);
    let updateAccount = spyOn(accountHandler, "updateAccount").and.returnValue(of(putAccount));

    let setActiveAccountSpy = spyOn(globalStorage, "setActiveAccount").and.stub();

    app.onClickSubmit("1", "Payment", "reference");
    expect(getActiveAccountSpy).toHaveBeenCalled();
    expect(createWithdrawSpy).toHaveBeenCalled();
    expect(updateAccount).toHaveBeenCalled();
    expect(setActiveAccountSpy).toHaveBeenCalled();
    
  })
});
