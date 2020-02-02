export class SectorDTO {
  sectorMark: string;
  numOfRows: number;
  numOfColumns: number;
  hallId: number;

  constructor() // sectorMark: string,
  // numOfRows: number,
  // numOfColumns: number,
  // hallId: number
  {
    // this.sectorMark = sectorMark;
    // this.numOfRows = numOfRows;
    // this.numOfColumns = numOfColumns;
    // this.hallId = hallId;
  }
}

export class Sector {
  private sectorMark: string;
  private numOfRows: number;
  private numOfColumns: number;
  private hallId: number;
  private eventSectors: [];
  private deleted: boolean;
}
