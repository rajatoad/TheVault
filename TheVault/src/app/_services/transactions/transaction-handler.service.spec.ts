import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { TransactionHandlerService } from './transaction-handler.service';

describe('TransactionHandlerService', () => {
  let service: TransactionHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(TransactionHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
