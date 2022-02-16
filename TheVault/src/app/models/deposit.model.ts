export class Deposit {

   
    constructor(private _depositid: number,
                private _accountid: number,
                private _reference: string,
                private _depositDate: number,
                private _amount: number
        ){

    }
    public get depositid(): number {
        return this._depositid;
    }
    public set depositid(value: number) {
        this._depositid = value;
    }
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get depositDate(): number {
        return this._depositDate;
    }
    public set depositDate(value: number) {
        this._depositDate = value;
    }
    public get reference(): string {
        return this._reference;
    }
    public set reference(value: string) {
        this._reference = value;
    }
    public get accountid(): number {
        return this._accountid;
    }
    public set accountid(value: number) {
        this._accountid = value;
    }



}
