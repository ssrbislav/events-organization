import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { PaymentService } from "../services/payment.service";
import { PaymentResponse } from "../model/paymentResponse";

@Component({
  selector: "app-payment",
  templateUrl: "./payment.component.html",
  styleUrls: ["./payment.component.css"]
})
export class PaymentComponent implements OnInit {
  private paymentId = "";
  private payerId = "";

  constructor(
    private route: ActivatedRoute,
    private paymentService: PaymentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.paymentId = this.route.snapshot.queryParams.paymentId;
    this.payerId = this.route.snapshot.queryParams.PayerID;
    this.completePayment();
  }

  completePayment() {
    this.paymentService.completePayment(this.paymentId, this.payerId).subscribe(
      (data: any) => {
        alert("Payment successfull!");
        this.router.navigate(["main"]);
      },
      error => {
        console.log(error);
      }
    );
  }
}
