import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WithdrawGenerateComponent } from './withdraw-generate.component';

describe('WithdrawGenerateComponent', () => {
  let component: WithdrawGenerateComponent;
  let fixture: ComponentFixture<WithdrawGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WithdrawGenerateComponent ]
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
