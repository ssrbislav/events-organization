import { Component, OnInit } from "@angular/core";
import { EventService } from "src/app/services/event.service";
import { MatDialogConfig, MatDialog } from "@angular/material";

@Component({
  selector: "app-event-list",
  templateUrl: "./event-list.component.html",
  styleUrls: ["./event-list.component.css"]
})
export class EventListComponent implements OnInit {
  private events: any;

  constructor(private eventService: EventService, private dialog: MatDialog) {}

  ngOnInit() {
    this.getEvents();
  }

  getEvents() {
    this.eventService.getEvents().subscribe(
      data => {
        this.events = data;
        console.log(this.events);
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
      id: 1
    };
  }
}
