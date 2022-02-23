export class Withdraw {
    public get amount(): number {
        return this._amount;
    }
  /* istanbul ignore next */
    public set amount(value: number) {
        this._amount = value;
    }
  /* istanbul ignore next */
    public get dateWithdraw(): string {
        return this._dateWithdraw;
    }
  /* istanbul ignore next */
    public set dateWithdraw(value: string) {
        this._dateWithdraw = value;
    }
  /* istanbul ignore next */
    public get reference(): string {
        return this._reference;
    }
  /* istanbul ignore next */
    public set reference(value: string) {
        this._reference = value;
    }
  /* istanbul ignore next */
    public get requestStatus(): string {
        return this._requestStatus;
    }
  /* istanbul ignore next */
    public set requestStatus(value: string) {
        this._requestStatus = value;
    }
  /* istanbul ignore next */
    public get requestType(): string {
        return this._requestType;
    }
  /* istanbul ignore next */
    public set requestType(value: string) {
        this._requestType = value;
    }
  /* istanbul ignore next */
    public get accountId(): number {
        return this._accountId;
    }
  /* istanbul ignore next */
    public set accountId(value: number) {
        this._accountId = value;
    }
  /* istanbul ignore next */
    public get withdrawId(): number {
        return this._withdrawId;
    }
  /* istanbul ignore next */
    public set withdrawId(value: number) {
        this._withdrawId = value;
    }
    constructor(
        private _withdrawId: number,
        private _accountId: number,
        private _requestType: string,
        private _requestStatus: string,
        private _reference: string,
        private _dateWithdraw: string,
        private _amount: number
    ){
        this._withdrawId = _withdrawId;
        this._accountId = _accountId;
        this._requestType = _requestType;
        this._requestStatus = _requestStatus;
        this._reference = _reference;
        this._dateWithdraw = _dateWithdraw;
        this._amount = _amount;
    }
}
