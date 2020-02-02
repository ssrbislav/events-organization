import { stringify } from "querystring";

export class HallDTO {
  private name: string;
  private locationId: number;

  constructor(name: string, locationId: number) {
    this.name = name;
    this.locationId = locationId;
  }
}

export class Hall {
  private id: number;
  private name: string;
  private sectors: [];
  private deleted: boolean;
}
