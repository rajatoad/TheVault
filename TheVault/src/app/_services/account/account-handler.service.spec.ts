import { TestBed } from '@angular/core/testing';

import { AccountHandlerService } from './account-handler.service';

describe('AccountHandlerService', () => {
  let service: AccountHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
