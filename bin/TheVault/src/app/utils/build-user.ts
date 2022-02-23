import { User } from "../models/users/user.model";

export default class BuildUser {

    constructor() { }

    static userBuilder(data: any) {
        let id: number = data.id;
        let username: string = data.username;
        let firstName: string = data.firstName;
        let lastName: string = data.lastName;
        let email: string = data.email;
        let address: string = data.address;
        let phoneNumber: number = data.phoneNumber;

        let user = new User(id, username, firstName, lastName, email, address, phoneNumber);

        return user;
    }

}
