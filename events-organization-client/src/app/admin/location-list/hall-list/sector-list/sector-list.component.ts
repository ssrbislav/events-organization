import { Component, OnInit, Inject } from "@angular/core";
import { Sector } from "src/app/model/sector.model";
import { MAT_DIALOG_DATA } from "@angular/material";

@Component({
  selector: "app-sector-list",
  templateUrl: "./sector-list.component.html",
  styleUrls: ["./sector-list.component.css"]
})
export class SectorListComponent implements OnInit {
  sectors: Sector[];

  constructor(@Inject(MAT_DIALOG_DATA) private data: any) {}

  ngOnInit() {
    this.sectors = this.data.hall.sectors;
  }
}
