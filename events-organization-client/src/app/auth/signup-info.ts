export class SignupInfo {
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  address: string;
  phoneNumber: string;

  constructor(
    username: string,
    email: string,
    password: string,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    address: string,
    phoneNumber: string
  ) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }
}
