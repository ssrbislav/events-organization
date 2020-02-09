import { Component, OnInit, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { EventService } from "src/app/services/event.service";
import { Event } from "src/app/model/event.model";
import { EventSector } from "src/app/model/eventSector.model";
import { TicketDTO } from "src/app/model/ticket.model";
import { TicketService } from "src/app/services/ticket.service";
import { Router } from "@angular/router";

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
  tickets: TicketDTO[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<any>,
    private eventService: EventService,
    private ticketService: TicketService,
    private router: Router
  ) {}

  ngOnInit() {
    this.dialogRef.updateSize("99%", "90%");

    this.event = this.data.event;
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

  reserveTickets() {
    this.checkedSeats.forEach(element => {
      const ticket: TicketDTO = new TicketDTO();
      let row;
      let column;
      let sectorMark;
      const temp = element.split(" ");
      [row, column, sectorMark] = temp;
      ticket.column = column;
      ticket.eventId = this.event.id;
      ticket.row = row;
      ticket.sectorMark = sectorMark;
      this.tickets.push(ticket);
    });
    console.log(this.tickets);
    if (this.tickets.length === 0) {
      alert("Select desired seats!");
    } else {
      this.ticketService
        .makeReservation(this.tickets, this.totalPrice)
        .subscribe(data => {
          console.log(data);
          alert("Reservation successful!");
          this.dialogRef.close();
          this.router.navigate(["../"]);
        });
    }
  }

  onCheckboxChange(option, es, event) {
    const elem = document.getElementsByClassName("seatinput");
    if (event.target.checked) {
      this.checkedSeats.push(event.target.value);
      this.calculatePrice(es.price);

      if (this.checkedSeats.length >= 5) {
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < elem.length; i++) {
          elem[i].setAttribute("disabled", "disabled");
        }
      }
    }
  }
}
