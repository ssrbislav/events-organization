import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogConfig, MatDialog } from "@angular/material";
import { HallService } from "src/app/services/hall.service";
import { Hall } from "src/app/model/hall.model";
import { SectorListComponent } from "./sector-list/sector-list.component";

@Component({
  selector: "app-hall-list",
  templateUrl: "./hall-list.component.html",
  styleUrls: ["./hall-list.component.css"]
})
export class HallListComponent implements OnInit {
  private halls: Hall[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialog: MatDialog,
    private hallService: HallService
  ) {}

  ngOnInit() {
    this.halls = this.data.location.halls;
  }

  listSectors(hall: Hall) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.data = {
      id: 1,
      hall
    };

    const dialog = this.dialog.open(SectorListComponent, dialogConfig);
  }
}
