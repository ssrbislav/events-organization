import { Component, OnInit } from "@angular/core";
import { LocationService } from "src/app/services/location.service";
import { Location } from "src/app/model/location.model";
import { MatDialogConfig, MatDialog } from "@angular/material";
import { HallListComponent } from "./hall-list/hall-list.component";

@Component({
  selector: "app-location-list",
  templateUrl: "./location-list.component.html",
  styleUrls: ["./location-list.component.css"]
})
export class LocationListComponent implements OnInit {
  private locations: Location[];

  constructor(
    private locationService: LocationService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.getLocations();
  }

  getLocations() {
    this.locationService.getLocations().subscribe(data => {
      this.locations = data;
    });
  }

  listHalls(location: Location) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      id: 1,
      location
    };

    const dialog = this.dialog.open(HallListComponent, dialogConfig);
  }
}
