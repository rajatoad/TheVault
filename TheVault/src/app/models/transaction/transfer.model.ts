export class Transfer {

  public get accountId(): number {
    return this._accountId;
  }
  /* istanbul ignore next */
  public set accountId(value: number) {
    this._accountId = value;
  }
  /* istanbul ignore next */
  public get receiverAccountId(): number {
    return this._receiverAccountId;
  }
  /* istanbul ignore next */
  public set receiverAccountId(value: number) {
    this._receiverAccountId = value;
  }
  /* istanbul ignore next */
  public get amount(): number {
    return this._amount;
  }
  /* istanbul ignore next */
  public set amount(value: number) {
    this._amount = value;
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
