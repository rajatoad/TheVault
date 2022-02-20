import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferGenerateComponent } from './transfer-generate.component';

describe('TransferGenerateComponent', () => {
  let component: TransferGenerateComponent;
  let fixture: ComponentFixture<TransferGenerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransferGenerateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransferGenerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
