import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { Event, EventDTO } from "src/app/model/event.model";
import { EventService } from "src/app/services/event.service";

@Component({
  selector: "app-add-event",
  templateUrl: "./add-event.component.html",
  styleUrls: ["./add-event.component.css"]
})
export class AddEventComponent implements OnInit {
  private locations: any;
  private form: any = {};
  private event: EventDTO = new EventDTO();
  private eventTypes = ["CONCERT", "FESTIVAL", "FAIR"];

  constructor(
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialogRef: MatDialogRef<AddEventComponent>,
    private eventService: EventService
  ) {}

  ngOnInit() {
    this.locations = this.data.data;
  }

  onSubmit() {
    this.event = this.form;

    this.eventService.createEvent(this.event).subscribe(
      data => {
        this.dialogRef.close();
      },
      error => {
        console.log(error);
        alert("Error occured!");
      }
    );
  }
}
