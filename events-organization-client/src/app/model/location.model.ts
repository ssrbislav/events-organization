export class LocationDTO {
  private name: string;
  private streetName: string;
  private number: number;
  private city: string;
  private zipCode: string;
  private country: string;

  constructor(
    name: string,
    streetName: string,
    number: number,
    city: string,
    zipCode: string,
    country: string
  ) {
    this.name = name;
    this.streetName = streetName;
    this.number = number;
    this.city = city;
    this.zipCode = zipCode;
    this.country = country;
  }
}

export class Location {
  private id: number;
  private name: string;
  private streetName: string;
  private number: number;
  private city: string;
  private zipCode: string;
  private country: string;
  private deleted: boolean;
  private halls: [];
  private events: [];
}
