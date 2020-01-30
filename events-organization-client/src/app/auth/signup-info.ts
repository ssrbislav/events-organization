export class SignupInfo {
  email: string;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  address: string;
  phoneNumber: string;
  dateOfBirth: string;

  constructor(
    email: string,
    username: string,
    password: string,
    firstName: string,
    lastName: string,
    address: string,
    phoneNumber: string,
    dateOfBirth: string
  ) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
  }
}
