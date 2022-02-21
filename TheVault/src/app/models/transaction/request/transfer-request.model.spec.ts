import { TransferRequest } from './transfer-request.model';

describe('TransferRequest', () => {
  it('should create an instance', () => {
    let transferRequest: TransferRequest = new TransferRequest(
      1,
      2,
      3333
    );
    expect(transferRequest).toBeTruthy();
  });
});
