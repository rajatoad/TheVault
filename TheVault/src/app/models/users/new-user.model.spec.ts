import { NewUser } from './new-user.model';

describe('NewUser', () => {
  it('should create an instance', () => {
    let newUser: NewUser = new NewUser(
      "username",
      "firstName",
      "lastName",
      "email@email.com",
      "123 Address",
      1112223333,
      "password"
    );
    expect(newUser).toBeTruthy();
  });
});
