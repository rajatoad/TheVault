import { WithdrawRequest } from './withdraw-request.model';

describe('WithdrawRequest', () => {
  it('should create an instance', () => {

    let withdrawRequest: WithdrawRequest = new WithdrawRequest(
      1,
      "Retail",
      "reference",
      1111
    );
    expect(withdrawRequest).toBeTruthy();
  });
});
