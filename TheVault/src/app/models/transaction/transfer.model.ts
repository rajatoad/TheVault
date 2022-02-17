export class Transfer {

  public get accountId(): number {
    return this._accountId;
  }
  public set accountId(value: number) {
    this._accountId = value;
  }
  public get accountType(): string {
    return this._accountType;
  }
  public set accountType(value: string) {
    this._accountType = value;
  }
  public get availableBalance(): number {
    return this._availableBalance;
  }
  public set availableBalance(value: number) {
    this._availableBalance = value;
  }
  public get pendingBalance(): number {
    return this._pendingBalance;
  }
  public set pendingBalance(value: number) {
    this._pendingBalance = value;
  }


  constructor(
    private _accountId: number,
    private _accountType: string,
    private _availableBalance: number,
    private _pendingBalance: number
  ) {
    this._accountId = _accountId;
    this._accountType = _accountType;
    this._availableBalance = _availableBalance;
    this._pendingBalance = _pendingBalance;
  }
}
