import { fakeAsync, TestBed, tick } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

import { RoutingAllocatorService } from './routing-allocator.service';
import { Location, LocationStrategy } from '@angular/common';
import { AppComponent } from 'src/app/app.component';

describe('RoutingAllocatorService', () => {
  let component = RoutingAllocatorService;
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

  it("fakeAsync works", fakeAsync(() => {
    let promise = new Promise(resolve => {
      setTimeout(resolve, 10);
    });
    let done = false;
    promise.then(() => (done = true));
    tick(50);
    expect(done).toBeTruthy();
  }));

  // it('should navigate to "" redirects to /register', fakeAsync(() => {
  //   router.navigate(['register']);
  //   tick();
  //   expect(location.path()).toBe('/register')
  // }))

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
