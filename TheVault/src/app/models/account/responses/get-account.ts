import { Account } from "../account.model";

export interface GetAccount {
    success: boolean,
    gotObject: Account[]
}
