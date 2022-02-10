import { TestBed } from '@angular/core/testing';

import { RoutingAllocatorService } from './routing-allocator.service';

describe('RoutingAllocatorService', () => {
  let service: RoutingAllocatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoutingAllocatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
