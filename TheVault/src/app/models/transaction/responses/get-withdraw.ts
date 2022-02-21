import { Withdraw } from "../withdraw.model";

export interface GetWithdraw {
    success: boolean;
    gotObject: Withdraw[];
}
