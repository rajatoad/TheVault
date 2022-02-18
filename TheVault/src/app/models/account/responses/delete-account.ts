import { Account } from "../account.model";

export interface DeleteAccount {
    success: boolean;
    deletedObject: Account[];
}
