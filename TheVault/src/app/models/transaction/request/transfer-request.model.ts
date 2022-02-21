export class TransferRequest {

  constructor(
    private _ownerAccountId: number,
    private _receiverAccountId: number,
    private _amount: number
  ) {
    this._ownerAccountId = _ownerAccountId;
    this._receiverAccountId = _receiverAccountId;
    this._amount = _amount;
  }
  public get ownerAccountId(): number {
    return this._ownerAccountId;
  }
  public set ownerAccountId(value: number) {
    this._ownerAccountId = value;
  }
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
}
