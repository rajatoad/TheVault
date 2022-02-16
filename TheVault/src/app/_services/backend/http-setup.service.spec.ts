import { TestBed } from '@angular/core/testing';

import { HttpSetupService } from './http-setup.service';

describe('HttpSetupService', () => {
  let service: HttpSetupService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpSetupService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
