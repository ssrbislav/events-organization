import { Component, OnInit } from "@angular/core";
import { EventService } from "src/app/services/event.service";
import { MatDialogConfig, MatDialog } from "@angular/material";
import { AddEventComponent } from "./add-event/add-event.component";
import { LocationService } from "src/app/services/location.service";
import { DefineEventsectorsComponent } from "./define-eventsectors/define-eventsectors.component";

@Component({
  selector: "app-event-list",
  templateUrl: "./event-list.component.html",
  styleUrls: ["./event-list.component.css"]
})
export class EventListComponent implements OnInit {
  private events: any;
  private locations: any;

  constructor(
    private eventService: EventService,
    private dialog: MatDialog,
    private locationServis: LocationService
  ) {}

  ngOnInit() {
    this.getEvents();
    this.getLocations();
  }

  getLocations() {
    this.locationServis.getLocations().subscribe(data => {
      this.locations = data;
    });
  }

  getEvents() {
    this.eventService.getEvents().subscribe(
      data => {
        this.events = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  addNewEvent() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      id: 1,
      data: this.locations
    };

    const dialog = this.dialog.open(AddEventComponent, dialogConfig);
  }

  defineES(event: any) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      id: 1,
      event
    };

    const dialog = this.dialog.open(DefineEventsectorsComponent, dialogConfig);
  }
}
