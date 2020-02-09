import { Component, OnInit } from "@angular/core";
import { EventService } from "src/app/services/event.service";
import { Event } from "src/app/model/event.model";

@Component({
  selector: "app-events",
  templateUrl: "./events.component.html",
  styleUrls: ["./events.component.css"]
})
export class EventsComponent implements OnInit {
  private events: any;
  private event: any;

  constructor(private eventService: EventService) {}

  ngOnInit() {
    this.getEvents();
  }

  getEvents() {
    this.eventService.getEvents().subscribe(data => {
      this.events = data;
    });
  }

  onEventInfo(event: any) {
    this.event = event;
  }
}
