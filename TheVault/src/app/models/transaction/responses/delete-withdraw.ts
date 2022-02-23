import { Withdraw } from "../withdraw.model";

export interface DeleteWithdraw {
    success:boolean;
    deletedObject:Withdraw[];
}
