import { stringify } from "querystring";

export class HallDTO {
  name: string;
  locationId: number;

  constructor() {}
}

export class Hall {
  private id: number;
  private name: string;
  private sectors: [];
  private deleted: boolean;
}
