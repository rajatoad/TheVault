import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { UserHandlerService } from './user-handler.service';

describe('UserHandlerService', () => {
  let service: UserHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(UserHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
