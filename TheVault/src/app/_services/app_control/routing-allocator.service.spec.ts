import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { RoutingAllocatorService } from './routing-allocator.service';

describe('RoutingAllocatorService', () => {
  let service: RoutingAllocatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule]
    });
    service = TestBed.inject(RoutingAllocatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
