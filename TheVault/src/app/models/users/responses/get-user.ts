import { Profile } from "../profile.model";

export interface GetUser {
    success: boolean,
    gotObject: Profile[]
}
