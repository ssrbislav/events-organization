import { Location } from "./location.model";

export class EventDTO {
  name: string;
  startDate: Date;
  endDate: Date;
  eventType: string;
  locationId: number;

  constructor() {}
}

export class Event {
  id: number;
  name: string;
  startDate: Date;
  endDate: Date;
  eventType: string;
  location: Location;
  eventSector: any;
  deleted: boolean;

  constructor() {}
}
