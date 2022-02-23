import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Profile } from 'src/app/models/users/profile.model';
import { RoutingAllocatorService } from 'src/app/_services/app_control/routing-allocator.service';

import { AccountProfileComponent } from './account-profile.component';

export class MockRouter extends RoutingAllocatorService{
  public override editProfile(): void {
      return;
  }
}

describe('AccountProfileComponent', () => {
  let component: AccountProfileComponent;
  let fixture: ComponentFixture<AccountProfileComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountProfileComponent ],
      imports: [RouterTestingModule],
      providers: [
        {
          provider: RoutingAllocatorService,
          useClass: MockRouter
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountProfileComponent);
    component = fixture.componentInstance;
    component.profile = new Profile(1, 1, "name", "name", "email@email.com", "1231231234", "123 address")
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should route to editProfile component on calling the method editProfile', () => {
    const fixture = TestBed.createComponent(AccountProfileComponent);
    const app = fixture.componentInstance;

    let router = fixture.debugElement.injector.get(RoutingAllocatorService);
    let routingSpy = spyOn(router, "editProfile").and.stub();

    app.editProfile();
    expect(routingSpy).toHaveBeenCalled();
  })

});
