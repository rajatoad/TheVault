export class NewUser {
    
    constructor(
        private _username: string,
        private _firstName: string,
        private _lastName: string,
        private _email: string,
        private _address: string,
        private _phoneNumber: number,
        private _password: string
    ) 
    {
        this._username = _username,
        this._firstName = _firstName,
        this._lastName = _lastName,
        this._email = _email,
        this._address = _address,
        this._phoneNumber = _phoneNumber,
        this._password = this._password
    }

  /* istanbul ignore next */
    public get username(): string {
        return this._username;
    }
  /* istanbul ignore next */
    public set username(value: string) {
        this._username = value;
    }

  /* istanbul ignore next */
    public get firstName(): string {
        return this._firstName;
    }
  /* istanbul ignore next */
    public set firstName(value: string) {
        this._firstName = value;
    }

  /* istanbul ignore next */
    public get lastName(): string {
        return this._lastName;
    }
  /* istanbul ignore next */
    public set lastName(value: string) {
        this._lastName = value;
    }

  /* istanbul ignore next */
    public get email(): string {
        return this._email;
    }
  /* istanbul ignore next */
    public set email(value: string) {
        this._email = value;
    }

  /* istanbul ignore next */
    public get address(): string {
        return this._address;
    }
  /* istanbul ignore next */
    public set address(value: string) {
        this._address = value;
    }

  /* istanbul ignore next */
    public get phoneNumber(): number {
        return this._phoneNumber;
    }
  /* istanbul ignore next */
    public set phoneNumber(value: number) {
        this._phoneNumber = value;
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
