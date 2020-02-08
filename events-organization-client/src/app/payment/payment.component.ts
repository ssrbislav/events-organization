import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { PaymentService } from "../services/payment.service";

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
    this.paymentId = this.route.snapshot.paramMap.get("paymentId");
    this.payerId = this.route.snapshot.paramMap.get("PayerId");
    this.completePayment();
  }

  completePayment() {
    this.paymentService.completePayment(this.paymentId, this.payerId).subscribe(
      data => {
        alert("Payment successfull!");
        console.log(data);
        this.router.navigate(["main"]);
      },
      error => {
        console.log(error);
      }
    );
  }
}
