import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { PutAccount } from 'src/app/models/account/responses/put-account';
import { Deposit } from 'src/app/models/transaction/deposit.model';
import { DepositRequest } from 'src/app/models/transaction/request/deposit-request.model';
import { PostDeposit } from 'src/app/models/transaction/responses/post-deposit';
import { AccountHandlerService } from 'src/app/_services/account/account-handler.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';

import { DepositGenerateComponent } from './deposit-generate.component';

class MockGlobalStorage extends GlobalStorageService{

}

class MockAccountHandler extends AccountHandlerService{

}

class MockTransactionHandler extends TransactionHandlerService{

}

describe('DepositGenerateComponent', () => {
  let component: DepositGenerateComponent;
  let fixture: ComponentFixture<DepositGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepositGenerateComponent ],
      imports: [HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepositGenerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should on submit create a deposit if it is valid', () => {
    const fixture = TestBed.createComponent(DepositGenerateComponent);
    const app = fixture.componentInstance;

    let activeAccount: Account = new Account(1, 1, "Savings", 222, 222);

    let depositRequest: DepositRequest = new DepositRequest("Cash", 1, "reference", 1);

    let deposit: Deposit = new Deposit(1, 1, "Cash", "reference", (Date.now).toString(), 1);

    let postDeposit: PostDeposit = {
      success: true,
      createdObject: [deposit]
    };

    let putAccount: PutAccount = {
      success: true,
      updatedObject: [new Account(1, 1, "Savings", 223, 223)]
    };

    let transactionHandler = fixture.debugElement.injector.get(TransactionHandlerService);
    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let accountHandler = fixture.debugElement.injector.get(AccountHandlerService);

    let createDepositSpy = spyOn(transactionHandler, "createDeposit").and.returnValue(of(postDeposit));
    let getActiveAccountSpy = spyOn(globalStorage, "getActiveAccount").and.returnValue(activeAccount);
    let updateAccountSpy = spyOn(accountHandler, "updateAccount").and.returnValue(of(putAccount));

    let setActiveAccount = spyOn(globalStorage, "setActiveAccount").and.stub();
    
    app.onClickSubmit("1", "Cash", "reference");
    expect(createDepositSpy).toHaveBeenCalled();
    expect(getActiveAccountSpy).toHaveBeenCalled();
    expect(updateAccountSpy).toHaveBeenCalled();
    expect(setActiveAccount).toHaveBeenCalled();
  });
});
