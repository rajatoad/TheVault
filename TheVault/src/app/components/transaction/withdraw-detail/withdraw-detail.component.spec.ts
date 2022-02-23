import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WithdrawDetailComponent } from './withdraw-detail.component';

describe('WithdrawDetailComponent', () => {
  let component: WithdrawDetailComponent;
  let fixture: ComponentFixture<WithdrawDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WithdrawDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WithdrawDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
