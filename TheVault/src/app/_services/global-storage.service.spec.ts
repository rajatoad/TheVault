import { TestBed } from '@angular/core/testing';

import { GlobalStorageService } from './global-storage.service';

describe('GlobalStorageService', () => {
  let service: GlobalStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
