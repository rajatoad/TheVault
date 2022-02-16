export class DepositRequest {
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
    public get depositType(): string {
        return this._depositType;
    }
    public set depositType(value: string) {
        this._depositType = value;
    }
    constructor(
        private _depositType: string,
        private _accountId: number,
        private _reference: string,
        private _amount: number
    ){
        this._depositType = _depositType;
        this._accountId = _accountId;
        this._reference = _reference;
        this._amount = _amount;
    }
}
