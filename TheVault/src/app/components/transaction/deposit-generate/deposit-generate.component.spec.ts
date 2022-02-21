import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
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
});
