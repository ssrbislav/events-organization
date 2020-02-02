import { Component, OnInit, Inject } from "@angular/core";
import { HallDTO } from "src/app/model/hall.model";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { HallService } from "src/app/services/hall.service";

@Component({
  selector: "app-add-hall",
  templateUrl: "./add-hall.component.html",
  styleUrls: ["./add-hall.component.css"]
})
export class AddHallComponent implements OnInit {
  hall: HallDTO = new HallDTO();

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<AddHallComponent>,
    private hallService: HallService
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.hall.locationId = this.data.locationId;

    console.log(this.hall);

    this.hallService.createHall(this.hall).subscribe(
      data => {
        alert("Hall succesfully created!");
        this.dialogRef.close();
      },
      error => {
        alert("Error occured!");
      }
    );
  }
}
