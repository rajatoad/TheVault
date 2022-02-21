import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Profile } from 'src/app/models/users/profile.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';

import { EditProfileComponent } from './edit-profile.component';

export class MockGlobalStorage extends GlobalStorageService{
  public override setProfile(user: Profile): void {
      return;
  }

  public override getProfile(): Profile {
      return new Profile(
        1,
        1,
        "name",
        "name",
        "email@email.com",
        "1231231234",
        "123 Address"
      );
  }
}

describe('EditProfileComponent', () => {
  let component: EditProfileComponent;
  let fixture: ComponentFixture<EditProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProfileComponent ],
      providers: [
        {
          providers: GlobalStorageService,
          useClass: MockGlobalStorage
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProfileComponent);
    component = fixture.componentInstance;
    component.profile = new Profile(
        1,
        1,
        "name",
        "name",
        "email@email.com",
        "1231231234",
        "123 Address"
    );
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
