export class Withdraw {
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get dateWithdraw(): string {
        return this._dateWithdraw;
    }
    public set dateWithdraw(value: string) {
        this._dateWithdraw = value;
    }
    public get reference(): string {
        return this._reference;
    }
    public set reference(value: string) {
        this._reference = value;
    }
    public get requestStatus(): string {
        return this._requestStatus;
    }
    public set requestStatus(value: string) {
        this._requestStatus = value;
    }
    public get requestType(): string {
        return this._requestType;
    }
    public set requestType(value: string) {
        this._requestType = value;
    }
    public get accountId(): number {
        return this._accountId;
    }
    public set accountId(value: number) {
        this._accountId = value;
    }
    public get withdrawId(): number {
        return this._withdrawId;
    }
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
