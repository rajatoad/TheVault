import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Profile } from 'src/app/models/users/profile.model';
import { GlobalStorageService } from 'src/app/_services/global-storage.service';
import { SpyLocation } from '@angular/common/testing'

import { EditProfileComponent } from './edit-profile.component';
import { UserHandlerService } from 'src/app/_services/user/user-handler.service';
import { PutProfile } from 'src/app/models/users/responses/put-profile';
import { of } from 'rxjs';

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
      imports: [HttpClientTestingModule],
      providers: [
        {
          providers: GlobalStorageService,
          useClass: MockGlobalStorage
        },
        {
          providers: Location,
          useClass: SpyLocation
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

  it('should on initializing, setup the profile with the profile of the user', () => {
    const fixture = TestBed.createComponent(EditProfileComponent);
    const app = fixture.componentInstance;

    let profile: Profile = new Profile(
      1,
      1,
      "firstName",
      "lastName",
      "email@email.com",
      "1231231234",
      "123 address"
    );

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let getProfileSpy = spyOn(globalStorage, "getProfile").and.returnValue(profile);

    app.setupProfile();
    expect(getProfileSpy).toHaveBeenCalled();

    expect(app.firstName).toBe(profile.firstName);
    expect(app.lastName).toBe(profile.lastName);
    expect(app.email).toBe(profile.email);
    expect(app.phoneNumber).toBe(profile.phoneNumber);
    expect(app.address).toBe(profile.address);
  });
  
  it('should on submit update the profile on component and global storage', () => {
    const fixture = TestBed.createComponent(EditProfileComponent);
    const app = fixture.componentInstance;

    let profile: Profile = new Profile(
      1,
      1,
      "firstName",
      "lastName",
      "email@email.com",
      "1231231234",
      "123 address"
    );

    let newProfile: Profile = new Profile(1, 1, "newFirst", "newLast", "email@email.com", "1231231234", "123 address");

    let putProfile: PutProfile = {
      success: true,
      updatedObject: [newProfile]
    }

    let globalStorage = fixture.debugElement.injector.get(GlobalStorageService);
    let getProfileSpy = spyOn(globalStorage, "getProfile").and.returnValue(profile);
    let setProfileSpy = spyOn(globalStorage, "setProfile").and.stub();

    let userIdSpy = spyOn(globalStorage, "getUserId").and.returnValue(1);

    let userHandler = fixture.debugElement.injector.get(UserHandlerService);
    let updateProfileSpy = spyOn(userHandler, "updateProfile").and.returnValue(of(putProfile));

    let goBackSpy = spyOn(app, 'goBack').and.callThrough();

    app.setupProfile();
    expect(getProfileSpy).toHaveBeenCalled();
    app.firstName = newProfile.firstName;
    app.lastName = newProfile.lastName;
    app.submit();
    expect(setProfileSpy).toHaveBeenCalled();
    expect(app.firstName).toBe(profile.firstName);
    expect(app.lastName).toBe(profile.lastName);
    expect(updateProfileSpy).toHaveBeenCalled();
    expect(userIdSpy).toHaveBeenCalled();
    expect(goBackSpy).toHaveBeenCalled();
  })
});
