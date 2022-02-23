import { Profile } from "../profile.model";

export interface PutProfile {
    success: boolean;
    updatedObject: Profile[];
}
