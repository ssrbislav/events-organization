import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { EventService } from "src/app/services/event.service";
import { Event } from "src/app/model/event.model";
import { EventSector } from "src/app/model/eventSector.model";

@Component({
  selector: "app-make-reservation",
  templateUrl: "./make-reservation.component.html",
  styleUrls: ["./make-reservation.component.css"]
})
export class MakeReservationComponent implements OnInit {
  private event: Event;
  private eventSec = [];
  private numOfCols: number[] = [];
  private numOfRows: number[] = [];
  private numOfSeats: number[][] = [[], []];
  private sector: any;
  private Arr = Array;
  private id;
  private checkedSeats = [];
  private totalPrice = 0;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<any>,
    private eventService: EventService
  ) {}

  ngOnInit() {
    this.dialogRef.updateSize("99%", "90%");

    this.event = this.data.event;
    console.log(this.event.eventSector);
  }

  check(event: EventSector, k: number) {
    if (true) {
      this.addClass(event, k);
    }
  }

  addClass(event: EventSector, k: number) {
    const element = document.getElementById(k.toString());
    if (event.sectorType === "VIP") {
      element.style.backgroundColor = "#009688";
    } else if (event.sectorType === "LOVE") {
      element.style.backgroundColor = "#066688";
    } else {
      element.style.backgroundColor = "#119688";
    }
    return true;
  }

  calculatePrice(price: number) {
    this.totalPrice += price;
  }

  onCheckboxChange(option, es, event) {
    const elem = document.getElementsByClassName("seatinput");
    if (event.target.checked) {
      this.checkedSeats.push(event.target.value);
      this.calculatePrice(es.price);

      console.log(this.checkedSeats);
      if (this.checkedSeats.length >= 5) {
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < elem.length; i++) {
          elem[i].setAttribute("disabled", "disabled");
        }
      }
    }
  }
}
