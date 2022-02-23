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

  /* istanbul ignore next */
    public get password(): string {
        return this._password;
    }
  /* istanbul ignore next */
    public set password(value: string) {
        this._password = value;
    }
    public get username(): string {
        return this._username;
    }
  /* istanbul ignore next */
    public set username(value: string) {
        this._username = value;
    }
    public get userId(): number {
        return this._userId;
    }
  /* istanbul ignore next */
    public set userId(value: number) {
        this._userId = value;
    }
}
