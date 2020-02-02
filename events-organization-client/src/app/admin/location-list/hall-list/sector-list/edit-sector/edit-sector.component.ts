import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material";
import { Sector } from "src/app/model/sector.model";

@Component({
  selector: "app-edit-sector",
  templateUrl: "./edit-sector.component.html",
  styleUrls: ["./edit-sector.component.css"]
})
export class EditSectorComponent implements OnInit {
  sector: Sector;

  constructor(@Inject(MAT_DIALOG_DATA) private data: any) {}

  ngOnInit() {
    this.sector = this.data.sector;
    console.log(this.sector);
  }
}
