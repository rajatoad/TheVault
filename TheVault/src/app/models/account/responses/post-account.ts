import { Account } from "../account.model";

export interface PostAccount {
    success: boolean;
    createdObject: Account[];
}
