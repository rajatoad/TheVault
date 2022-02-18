import { DatePipe } from "@angular/common";

export class Transaction {
    public get transactionReference(): string {
        return this._transactionReference;
    }
    public set transactionReference(value: string) {
        this._transactionReference = value;
    }
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get date(): string {
        return this._date;
    }
    public set date(value: string) {
        this._date = value;
    }
    public get transactionType(): string {
        return this._transactionType;
    }
    public set transactionType(value: string) {
        this._transactionType = value;
    }
    
    public get transactionId(): number {
        return this._transactionId;
    }
    public set transactionId(value: number) {
        this._transactionId = value;
    }
    constructor(
        private _transactionId: number,
        private _transactionType: string,
        private _transactionReference: string,
        private _date: string,
        private _amount: number
    ){
        this._transactionId = _transactionId;
        this._transactionType = _transactionType;
        this._date = _date;
        this._amount = _amount;
    }
}
