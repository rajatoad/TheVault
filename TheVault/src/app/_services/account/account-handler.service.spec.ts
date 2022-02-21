import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { AccountHandlerService } from './account-handler.service';

describe('AccountHandlerService', () => {
  let service: AccountHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(AccountHandlerService);
  });

});
