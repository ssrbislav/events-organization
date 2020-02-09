import { Component, OnInit, Inject } from "@angular/core";
import { Sector } from "src/app/model/sector.model";
import { MAT_DIALOG_DATA, MatDialog, MatDialogConfig } from "@angular/material";
import { Hall } from "src/app/model/hall.model";
import { AddSectorComponent } from "./add-sector/add-sector.component";
import { EditSectorComponent } from "./edit-sector/edit-sector.component";

@Component({
  selector: "app-sector-list",
  templateUrl: "./sector-list.component.html",
  styleUrls: ["./sector-list.component.css"]
})
export class SectorListComponent implements OnInit {
  sectors: Sector[];
  hall: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.hall = this.data.hall;
    this.sectors = this.hall.sectors;
  }

  addNewSector(hallId: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      id: 1,
      hallId
    };

    const dialog = this.dialog.open(AddSectorComponent, dialogConfig);
  }

  editSector(sector: Sector) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      id: 1,
      sector
    };

    const dialog = this.dialog.open(EditSectorComponent, dialogConfig);
  }
}
