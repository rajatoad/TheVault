export class TransferRequest {

  public get amount(): number {
    return this._amount;
  }
  public set amount(value: number) {
    this._amount = value;
  }
  public get receiverAccountId(): number {
    return this._receiverAccountId;
  }
  public set receiverAccountId(value: number) {
    this._receiverAccountId = value;
  }
  public get accountId(): number {
    return this._accountId;
  }
  public set accountId(value: number) {
    this._accountId = value;
  }

  constructor(
    private _accountId: number,
    private _receiverAccountId: number,
    private _amount: number
  ) {
    this._accountId = _accountId;
    this._receiverAccountId = _receiverAccountId;
    this._amount = _amount;
  }
}
