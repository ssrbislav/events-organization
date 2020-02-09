import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { Sector, SectorDTO } from "src/app/model/sector.model";
import { SectorService } from "src/app/services/sector.service";

@Component({
  selector: "app-add-sector",
  templateUrl: "./add-sector.component.html",
  styleUrls: ["./add-sector.component.css"]
})
export class AddSectorComponent implements OnInit {
  sector: SectorDTO = new SectorDTO();

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<AddSectorComponent>,
    private sectorService: SectorService
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.sector.hallId = this.data.hallId;

    console.log(this.sector);

    this.sectorService.createSector(this.sector).subscribe(
      data => {
        alert("Sector succesfully created!");
        this.dialogRef.close();
      },
      error => {
        alert("Error occured!");
      }
    );
  }
}
