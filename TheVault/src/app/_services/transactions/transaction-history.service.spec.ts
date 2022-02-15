import { TestBed } from '@angular/core/testing';

import { TransactionHistoryService } from './transaction-history.service';

describe('TransactionHistoryService', () => {
  let service: TransactionHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransactionHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
