import { Component, OnInit, Inject } from "@angular/core";
import { LocationService } from "src/app/services/location.service";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { LocationDTO } from "src/app/model/location.model";

@Component({
  selector: "app-add-location",
  templateUrl: "./add-location.component.html",
  styleUrls: ["./add-location.component.css"]
})
export class AddLocationComponent implements OnInit {
  location: LocationDTO = new LocationDTO();

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<AddLocationComponent>,
    private locationService: LocationService
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.locationService.createLocation(this.location).subscribe(
      data => {
        alert("Location succesfully added!");
        this.dialogRef.close();
      },
      error => {
        alert("Error occured!");
      }
    );
  }
}
