import { TestBed } from '@angular/core/testing';

import { WithdrawGenerateService } from './withdraw-generate.service';

describe('WithdrawGenerateService', () => {
  let service: WithdrawGenerateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WithdrawGenerateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
