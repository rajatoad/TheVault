import { LoginUser } from './login-user.model';

describe('LoginUser', () => {
  it('should create an instance', () => {
    let loginUser: LoginUser = new LoginUser(
      "username",
      "password"
    );
    expect(loginUser).toBeTruthy();
  });
});
