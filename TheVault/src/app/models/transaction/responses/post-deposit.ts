import { Deposit } from "../deposit.model";

export interface PostDeposit {
    success: boolean;
    createdObject: Deposit[];
}
