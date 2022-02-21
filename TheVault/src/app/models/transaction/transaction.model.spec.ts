import { Transaction } from './transaction.model';

describe('Transaction', () => {
  it('should create an instance', () => {
    let transaction: Transaction = new Transaction(
      1,
      "Deposit",
      "Cash",
      "reference",
      `${Date.now}`,
      1111
    );
    expect(transaction).toBeTruthy();
  });
});
