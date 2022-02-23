import { Transaction } from "../transaction.model";

export interface GetTransaction {
    success: boolean;
    gotObject: Transaction[];
}
