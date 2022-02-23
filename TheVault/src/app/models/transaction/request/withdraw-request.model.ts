export class WithdrawRequest {
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get reference(): string {
        return this._reference;
    }
    public set reference(value: string) {
        this._reference = value;
    }
    public get accountId(): number {
        return this._accountId;
    }
    public set accountId(value: number) {
        this._accountId = value;
    }
    public get requestType(): string {
        return this._requestType;
    }
    public set requestType(value: string) {
        this._requestType = value;
    }
    constructor(
        private _accountId: number,
        private _requestType: string,
        private _reference: string,
        private _amount: number
    ){
        this._requestType = _requestType;
        this._accountId = _accountId;
        this._reference = _reference;
        this._amount = _amount;
    }
}
