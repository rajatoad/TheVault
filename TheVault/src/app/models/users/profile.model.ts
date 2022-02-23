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

  /* istanbul ignore next */
    public get address(): string {
        return this._address;
    }
  /* istanbul ignore next */
    public set address(value: string) {
        this._address = value;
    }
  /* istanbul ignore next */
    public get phoneNumber(): string {
        return this._phoneNumber;
    }
  /* istanbul ignore next */
    public set phoneNumber(value: string) {
        this._phoneNumber = value;
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
    public get lastName(): string {
        return this._lastName;
    }
  /* istanbul ignore next */
    public set lastName(value: string) {
        this._lastName = value;
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
    public get userId(): number {
        return this._userId;
    }
  /* istanbul ignore next */
    public set userId(value: number) {
        this._userId = value;
    }
  /* istanbul ignore next */
    public get profileId(): number {
        return this._profileId;
    }
  /* istanbul ignore next */
    public set profileId(value: number) {
        this._profileId = value;
    }
}
