import { Withdraw } from './withdraw.model';

describe('Withdraw', () => {
  it('should create an instance', () => {
    let withdraw: Withdraw = new Withdraw(
      1,
      1,
      "Retail",
      "Pending",
      "reference",
      `${Date.now}`,
      1111
    );
    expect(withdraw).toBeTruthy();
  });
});
