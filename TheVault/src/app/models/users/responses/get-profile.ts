import { Profile } from "../profile.model";

export interface GetProfile {
    success: boolean,
    gotObject: Profile[]
}
