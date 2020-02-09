import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { EventService } from "src/app/services/event.service";
import { AddEventComponent } from "../add-event/add-event.component";
import { Event } from "src/app/model/event.model";
import { Hall } from "src/app/model/hall.model";
import { Location } from "src/app/model/location.model";
import { EventSector, EventSectorDTO } from "src/app/model/eventSector.model";
import { Sector } from "src/app/model/sector.model";

@Component({
  selector: "app-define-eventsectors",
  templateUrl: "./define-eventsectors.component.html",
  styleUrls: ["./define-eventsectors.component.css"]
})
export class DefineEventsectorsComponent implements OnInit {
  private minimum = 0;
  private event: Event = new Event();
  private halls: Hall[] = [];
  private eventSector: EventSectorDTO = new EventSectorDTO();
  private sectorTypes = ["REGULAR", "VIP", "LOVE"];
  private hall: Hall;
  private sectors: Sector[];

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<AddEventComponent>,
    private eventService: EventService
  ) {}

  ngOnInit() {
    this.event = this.data.event;
    this.halls = this.event.location.halls;
  }

  getHallSectors(hall: Hall) {
    this.sectors = hall.sectors;
  }

  onSubmit() {
    this.eventSector.eventId = this.event.id;
    this.eventService.createEventSector(this.eventSector).subscribe(
      data => {
        this.dialogRef.close();
      },
      error => {
        alert("Error occured");
        console.log(error);
      }
    );
  }
}
