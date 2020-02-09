import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { Sector } from "src/app/model/sector.model";
import { SectorService } from "src/app/services/sector.service";

@Component({
  selector: "app-edit-sector",
  templateUrl: "./edit-sector.component.html",
  styleUrls: ["./edit-sector.component.css"]
})
export class EditSectorComponent implements OnInit {
  sector: Sector;

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<EditSectorComponent>,
    private sectorService: SectorService
  ) {}

  ngOnInit() {
    this.sector = this.data.sector;
  }

  onSubmit() {
    this.sectorService.updateSector(this.sector).subscribe(
      data => {
        alert("Sector successfully updated!");
        this.dialogRef.close();
      },
      error => {
        console.log(error);
        alert("An error occured!");
      }
    );
  }
}
