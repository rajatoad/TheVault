import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectTransactionComponent } from './select-transaction.component';

describe('SelectTransactionComponent', () => {
  let component: SelectTransactionComponent;
  let fixture: ComponentFixture<SelectTransactionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectTransactionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
