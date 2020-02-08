import { Component, OnInit } from "@angular/core";
import { PaymentService } from "src/app/services/payment.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-event-info",
  templateUrl: "./event-info.component.html",
  styleUrls: ["./event-info.component.css"]
})
export class EventInfoComponent implements OnInit {
  private isShow = false;

  constructor(private paymentService: PaymentService, private router: Router) {}

  ngOnInit() {}

  toggleDisplay() {
    this.isShow = !this.isShow;
  }

  pay() {
    this.toggleDisplay();
    this.paymentService.makePayment("30").subscribe(
      (data: any) => {
        console.log(data);
        window.location.href = data.redirect_url;
      },
      error => {
        console.log(error);
        this.toggleDisplay();
      }
    );
  }
}
