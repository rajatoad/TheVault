export class LoginUser {

    constructor(
        private _username: string,
        private _password: string
    ) 
    { 
        this._username = _username,
        this._password = _password
    }

    public get username(): string {
        return this._username;
    }
  /* istanbul ignore next */
    public set username(value: string) {
        this._username = value;
    }

  /* istanbul ignore next */
    public get password(): string {
        return this._password;
    }
  /* istanbul ignore next */
    public set password(value: string) {
        this._password = value;
    }
}
