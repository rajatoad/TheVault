import { Deposit } from "../deposit.model";

export interface GetDeposit {
    success: boolean;
    gotObject: Deposit[];
}
