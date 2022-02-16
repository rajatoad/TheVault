export class Deposit {
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get dateDeposit(): string {
        return this._dateDeposit;
    }
    public set dateDeposit(value: string) {
        this._dateDeposit = value;
    }
    public get reference(): string {
        return this._reference;
    }
    public set reference(value: string) {
        this._reference = value;
    }
    public get depositType(): string {
        return this._depositType;
    }
    public set depositType(value: string) {
        this._depositType = value;
    }
    public get accountId(): number {
        return this._accountId;
    }
    public set accountId(value: number) {
        this._accountId = value;
    }
    public get depositId(): number {
        return this._depositId;
    }
    public set depositId(value: number) {
        this._depositId = value;
    }

    constructor(
        private _depositId: number,
        private _accountId: number,
        private _depositType: string,
        private _reference: string,
        private _dateDeposit: string,
        private _amount: number
    ){
        this._depositId = _depositId;
        this._accountId = _accountId;
        this._depositType = _depositType;
        this._reference = _reference;
        this._dateDeposit = _dateDeposit;
        this._amount = _amount;
    }
}
