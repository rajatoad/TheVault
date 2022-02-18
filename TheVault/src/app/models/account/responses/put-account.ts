import { Account } from "../account.model";

export interface PutAccount {
    success: boolean,
    updatedObject: Account[]
}
