import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositGenerateComponent } from './deposit-generate.component';

describe('DepositGenerateComponent', () => {
  let component: DepositGenerateComponent;
  let fixture: ComponentFixture<DepositGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepositGenerateComponent ]
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
