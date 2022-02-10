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

    public get username(): string {
        return this._username;
    }
    public set username(value: string) {
        this._username = value;
    }

    public get firstName(): string {
        return this._firstName;
    }
    public set firstName(value: string) {
        this._firstName = value;
    }

    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }

    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }

    public get address(): string {
        return this._address;
    }
    public set address(value: string) {
        this._address = value;
    }

    public get phoneNumber(): number {
        return this._phoneNumber;
    }
    public set phoneNumber(value: number) {
        this._phoneNumber = value;
    }
    
    public get password(): string {
        return this._password;
    }
    public set password(value: string) {
        this._password = value;
    }
}
