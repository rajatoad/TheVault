import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

import { RoutingAllocatorService } from './routing-allocator.service';
import { Location, LocationStrategy } from '@angular/common';
import { AppComponent } from 'src/app/app.component';

describe('RoutingAllocatorService', () => {
  let service: RoutingAllocatorService;
  let location: Location;
  let router: Router;
  let fixture;

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
