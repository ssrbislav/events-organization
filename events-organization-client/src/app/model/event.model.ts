export class EventDTO {
  name: string;
  startDate: Date;
  endDate: Date;
  eventType: string;
  locationId: number;

  constructor() {}
}

export class Event {
  private id: number;
  private name: string;
  private startDate: Date;
  private endDate: Date;
  private eventType: string;
  private eventSector: any;
  private deleted: boolean;

  constructor() {}
}
