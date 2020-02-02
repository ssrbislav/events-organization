import { Component, OnInit, Inject, AfterViewChecked } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { HallService } from "src/app/services/hall.service";
import { Hall } from "src/app/model/hall.model";

@Component({
  selector: "app-hall-list",
  templateUrl: "./hall-list.component.html",
  styleUrls: ["./hall-list.component.css"]
})
export class HallListComponent implements OnInit {
  private halls: Hall[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    // public dialogRef: MatDialogRef<Location>,
    private hallService: HallService
  ) {}

  ngOnInit() {
    this.halls = this.data.location.halls;
  }
}
