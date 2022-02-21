import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { Account } from 'src/app/models/account/account.model';
import { GetTransaction } from 'src/app/models/transaction/responses/get-transaction';
import { Transaction } from 'src/app/models/transaction/transaction.model';
import { Withdraw } from 'src/app/models/transaction/withdraw.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { TransactionHandlerService } from 'src/app/_services/transactions/transaction-handler.service';
import { HttpClientTestingModule } from '@angular/common/http/testing'

import { AccountDetailComponent } from './account-detail.component';
import { RouterTestingModule } from '@angular/router/testing';

export class MockGlobalStorage extends GlobalStorageService{
  public override getActiveAccount(): Account {
    let account:Account = new Account(
      1,
      1,
      "Checking",
      111,
      111
    );
    return account; 
  }
}

export class MockTransactionHandler extends TransactionHandlerService {
  public override getTransactionDetails(withdrawId: number): Observable<Withdraw> {
      let withdraw: Withdraw = new Withdraw(
        1,
        1,
        "Retail",
        "Pending",
        "reference",
        `${Date.now}`,
        1111
      );
      return of(withdraw);
  }

  public override getTransactionHistory(accountId: number): Observable<GetTransaction> {
      let getTransaction:GetTransaction = {
        success: true,
        gotObject: [new Transaction(1, "Deposit", "Retail", "reference", `${Date.now}`, 111)]
      }
      return of(getTransaction);
  }
}

export class MockRouter extends RoutingAllocatorService{
  public override login(): void {
    console.log("Yo");
  }
}

describe('AccountDetailComponent', () => {
  let component: AccountDetailComponent;
  let fixture: ComponentFixture<AccountDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountDetailComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [
        {
          provide: GlobalStorageService,
          userClass: MockGlobalStorage
        },
        {
          provide: TransactionHandlerService,
          userClass: MockTransactionHandler
        },
        {
          provide: RoutingAllocatorService,
          userClass: MockRouter
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
        
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should open deposit generator and close all other generators', () => {
    component.depositGenerator();
    expect(component.createDeposit).toBeTrue();
    expect(component.createTransfer).toBeFalse();
    expect(component.createWithdraw).toBeFalse();
  });

  it('should open withdraw generator and close all other generators', () =>{
    component.withdrawGenerator();
    expect(component.createDeposit).toBeFalse();
    expect(component.createTransfer).toBeFalse();
    expect(component.createWithdraw).toBeTrue();
  });

  it('should open transfer generator and close all other generators', () => {
    component.transferGenerator();
    expect(component.createDeposit).toBeFalse();
    expect(component.createTransfer).toBeTrue();
    expect(component.createWithdraw).toBeFalse();
  });

});
