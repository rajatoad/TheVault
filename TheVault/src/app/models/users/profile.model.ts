export class Profile {

    constructor(
        private _profileId: number,
        private _userId: number,
        private _firstName: string,
        private _lastName: string,
        private _email: string,
        private _phoneNumber: string,
        private _address: string
    ){
        this._profileId = _profileId;
        this._userId = _userId;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._email = _email;
        this._phoneNumber = _phoneNumber;
        this._address = _address;
    }

    public get address(): string {
        return this._address;
    }
    public set address(value: string) {
        this._address = value;
    }
    public get phoneNumber(): string {
        return this._phoneNumber;
    }
    public set phoneNumber(value: string) {
        this._phoneNumber = value;
    }
    public get email(): string {
        return this._email;
    }
    public set email(value: string) {
        this._email = value;
    }
    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }
    public get firstName(): string {
        return this._firstName;
    }
    public set firstName(value: string) {
        this._firstName = value;
    }
    public get userId(): number {
        return this._userId;
    }
    public set userId(value: number) {
        this._userId = value;
    }
    public get profileId(): number {
        return this._profileId;
    }
    public set profileId(value: number) {
        this._profileId = value;
    }
}
