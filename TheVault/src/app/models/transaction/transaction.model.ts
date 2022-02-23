import { DatePipe } from "@angular/common";

export class Transaction {
    public get transactionDetail(): string {
        return this._transactionDetail;
    }
  /* istanbul ignore next */
    public set transactionDetail(value: string) {
        this._transactionDetail = value;
    }
  /* istanbul ignore next */
    public get transactionReference(): string {
        return this._transactionReference;
    }
  /* istanbul ignore next */
    public set transactionReference(value: string) {
        this._transactionReference = value;
    }
  /* istanbul ignore next */
    public get amount(): number {
        return this._amount;
    }
  /* istanbul ignore next */
    public set amount(value: number) {
        this._amount = value;
    }
  /* istanbul ignore next */
    public get date(): string {
        return this._date;
    }
  /* istanbul ignore next */
    public set date(value: string) {
        this._date = value;
    }
  /* istanbul ignore next */
    public get transactionType(): string {
        return this._transactionType;
    }
  /* istanbul ignore next */
    public set transactionType(value: string) {
        this._transactionType = value;
    }
    
  /* istanbul ignore next */
    public get transactionId(): number {
        return this._transactionId;
    }
  /* istanbul ignore next */
    public set transactionId(value: number) {
        this._transactionId = value;
    }
    constructor(
        private _transactionId: number,
        private _transactionType: string,
        private _transactionDetail: string,
        private _transactionReference: string,
        private _date: string,
        private _amount: number
    ){
        this._transactionId = _transactionId;
        this._transactionType = _transactionType;
        this._transactionDetail = _transactionDetail;
        this._date = _date;
        this._amount = _amount;
    }
}
