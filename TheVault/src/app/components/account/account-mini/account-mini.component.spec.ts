import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountMiniComponent } from './account-mini.component';

describe('AccountMiniComponent', () => {
  let component: AccountMiniComponent;
  let fixture: ComponentFixture<AccountMiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountMiniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountMiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
