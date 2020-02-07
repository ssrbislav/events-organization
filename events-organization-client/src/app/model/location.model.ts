import { Hall } from "./hall.model";

export class LocationDTO {
  name: string;
  streetName: string;
  number: number;
  city: string;
  zipCode: string;
  country: string;

  constructor() {}
}

export class Location {
  id: number;
  name: string;
  streetName: string;
  number: number;
  city: string;
  zipCode: string;
  country: string;
  deleted: boolean;
  halls: Hall[];
}
