import { Transfer } from './transfer.model';

describe('Transfer', () => {
  it('should create an instance', () => {
    let transfer: Transfer = new Transfer(
      1,
      2,
      1111
    );

    expect(transfer).toBeTruthy();
  });
});
