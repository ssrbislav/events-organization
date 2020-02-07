export class EventSectorDTO {
  sectorId: number;
  eventId: number;
  price: number;
  sectorType: string;
}

export class EventSector {
  id: number;
  tickets: [];
  price: number;
  sectorType: string;
}
