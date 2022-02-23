import { Deposit } from './deposit.model';

describe('Deposit', () => {
  it('should create an instance', () => {
    let deposit: Deposit = new Deposit(
      1,
      1,
      "Cash",
      "reference",
      `${Date.now}`,
      1111
    );
    expect(deposit).toBeTruthy();
  });
});
