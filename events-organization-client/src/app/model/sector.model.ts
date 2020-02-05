export class SectorDTO {
  sectorMark: string;
  numOfRows: number;
  numOfColumns: number;
  hallId: number;

  constructor() {}
}

export class Sector {
  private sectorMark: string;
  private numOfRows: number;
  private numOfColumns: number;
  private hallId: number;
  private eventSectors: [];
  private deleted: boolean;
}
