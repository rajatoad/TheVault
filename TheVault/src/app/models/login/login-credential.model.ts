export class LoginCredential {
    constructor(
        private _userId: number,
        private _username: string,
        private _password: string
    ){
        this._userId = _userId;
        this._username = _username;
        this._password = _password;
    }

    public get password(): string {
        return this._password;
    }
    public set password(value: string) {
        this._password = value;
    }
    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
}
