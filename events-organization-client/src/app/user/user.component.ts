import { Component, OnInit } from "@angular/core";
import { TokenStorageService } from "../auth/ttoken-storage.service";
import { AuthService } from "../services/auth.service";
import { TicketService } from "../services/ticket.service";
import { PaymentService } from "src/app/services/payment.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"]
})
export class UserComponent implements OnInit {
  private reservations: [] = [];
  private ticketReservations: [] = [];
  private user: any;
  private isShow = false;

  constructor(
    private tokenStorage: TokenStorageService,
    private userService: AuthService,
    private ticketService: TicketService,
    private paymentService: PaymentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getUser();
  }

  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  cancelReservation(id: number) {
    this.ticketService.cancelReservation(id).subscribe(
      data => {
        console.log(data);
        alert("Reservation successfully canceled!");
        location.reload();
      },
      error => {
        console.log(error);
      }
    );
  }

  buy(reservation: any) {
    this.toggleDisplay();
    this.paymentService.makePayment(reservation.price).subscribe(
      (redirect: any) => {
        this.ticketService.butTicket(reservation.id).subscribe(
          data => {
            window.location.href = redirect.redirect_url;
          },
          error => {
            console.log(error);
          }
        );
      },
      error => {
        console.log(error);
        this.toggleDisplay();
      }
    );
  }

  getUser() {
    this.userService.getUser(this.tokenStorage.getUsername()).subscribe(
      data => {
        let res: any;
        this.user = data;
        res = this.user.reservations;
        this.ticketReservations = res;
        this.reservations = res.filter(re => re.deleted === false);
        console.log(this.reservations);
      },
      error => {
        console.log(error);
      }
    );
  }
}
