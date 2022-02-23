export class Deposit {
    public get amount(): number {
        return this._amount;
    }
  /* istanbul ignore next */
    public set amount(value: number) {
        this._amount = value;
    }
  /* istanbul ignore next */
    public get dateDeposit(): string {
        return this._dateDeposit;
    }
  /* istanbul ignore next */
    public set dateDeposit(value: string) {
        this._dateDeposit = value;
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
    public get depositType(): string {
        return this._depositType;
    }
  /* istanbul ignore next */
    public set depositType(value: string) {
        this._depositType = value;
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
    public get depositId(): number {
        return this._depositId;
    }
  /* istanbul ignore next */
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
    ) {
        this._depositId = _depositId;
        this._accountId = _accountId;
        this._depositType = _depositType;
        this._reference = _reference;
        this._dateDeposit = _dateDeposit;
        this._amount = _amount;
    }
}
