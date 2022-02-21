import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
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
});
