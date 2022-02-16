import { Withdraw } from "../withdraw.model";

export interface PostWithdraw {
    success: boolean;
    createdObject: Withdraw[];
}
