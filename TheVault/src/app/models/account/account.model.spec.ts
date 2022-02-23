import { Account } from './account.model';

describe('Account', () => {
  it('should create an instance', () => {
    let account:Account = new Account(
      1,
      1,
      "Checking",
      111,
      111
    );
    expect(account).toBeTruthy();
  });
});
