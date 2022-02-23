import { LoginCredential } from "../login-credential.model";

export interface PostLogin {
    success: boolean;
    createdObject: LoginCredential[];
}
