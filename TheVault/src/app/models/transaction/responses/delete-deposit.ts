import { Deposit } from "../deposit.model";

export interface DeleteDeposit {
    success:boolean;
    deletedObject: Deposit[];
}
