import { async, ComponentFixture, inject, TestBed } from '@angular/core/testing';
import { Account } from 'src/app/models/account/account.model';
import { Transaction } from 'src/app/models/transaction/transaction.model';

import { TransactionHistoryComponent } from './transaction-history.component';

describe('TransactionHistoryComponent', () => {
  let component: TransactionHistoryComponent;
  let fixture: ComponentFixture<TransactionHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionHistoryComponent ],
      imports: []
    })
    .compileComponents();
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionHistoryComponent);
    component = fixture.componentInstance;
    component.account = new Account(
        1,
        1,
        "Checking",
        111,
        111
    );
    component.transactions = [
      new Transaction(
        1,
        "Deposit",
        "Cash",
        "reference",
        `${Date.now}`,
        1111
      )
    ]
    fixture.detectChanges();
  });
  
//   it('set submit to true', async(() => {
//     component.ngOnInit();
//     expect(component).toBeTruthy();
// }))

});

//   it('should not create', () => {
//     expect(component).toBeFalsy();
//   });
// });
