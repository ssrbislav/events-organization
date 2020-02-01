import { Component, OnInit } from "@angular/core";
import { LocationService } from "src/app/services/location.service";
import { Location } from "src/app/model/location.model";

@Component({
  selector: "app-location-list",
  templateUrl: "./location-list.component.html",
  styleUrls: ["./location-list.component.css"]
})
export class LocationListComponent implements OnInit {
  private locations: Location[];

  constructor(private locationService: LocationService) {}

  ngOnInit() {
    this.getLocations();
  }

  getLocations() {
    this.locationService.getLocations().subscribe(data => {
      this.locations = data;
    });
  }
}
