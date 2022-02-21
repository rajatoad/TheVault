import { DepositRequest } from './deposit-request.model';

describe('DepositRequest', () => {
  it('should create an instance', () => {
    let depositRequest: DepositRequest = new DepositRequest(
      "Cash",
      1,
      "reference",
      1111
    );
    expect(depositRequest).toBeTruthy();
  });
});
