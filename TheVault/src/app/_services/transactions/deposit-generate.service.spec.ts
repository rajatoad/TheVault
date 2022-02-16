import { TestBed } from '@angular/core/testing';

import { DepositGenerateService } from './deposit-generate.service';

describe('DepositGenerateService', () => {
  let service: DepositGenerateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepositGenerateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
