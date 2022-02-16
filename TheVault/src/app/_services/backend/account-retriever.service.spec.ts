import { TestBed } from '@angular/core/testing';

import { AccountRetrieverService } from './account-retriever.service';

describe('AccountRetrieverService', () => {
  let service: AccountRetrieverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountRetrieverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
