import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { TransferGenerateService } from './transfer-generate.service';

describe('TransferGenerateService', () => {
  let service: TransferGenerateService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(TransferGenerateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
