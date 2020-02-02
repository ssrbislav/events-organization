import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material";
import { Sector } from "src/app/model/sector.model";

@Component({
  selector: "app-add-sector",
  templateUrl: "./add-sector.component.html",
  styleUrls: ["./add-sector.component.css"]
})
export class AddSectorComponent implements OnInit {
  sector: Sector = new Sector();

  constructor(@Inject(MAT_DIALOG_DATA) private data: any) {}

  ngOnInit() {
    console.log(this.sector);
  }
}
