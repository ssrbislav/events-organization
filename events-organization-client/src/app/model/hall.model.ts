import { stringify } from "querystring";

export class HallDTO {
  name: string;
  locationId: number;

  constructor() {}
}

export class Hall {
  id: number;
  name: string;
  sectors: [];
  deleted: boolean;
}
