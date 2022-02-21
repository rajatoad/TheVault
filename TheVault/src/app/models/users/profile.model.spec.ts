import { Profile } from './profile.model';

describe('Profile', () => {
  it('should create an instance', () => {
    let profile:Profile = new Profile(
        1,
        1,
        "firstName",
        "lastName",
        "email@email.com",
        "1112223333",
        "123 address"
      );
    expect(profile).toBeTruthy();
  });
});
