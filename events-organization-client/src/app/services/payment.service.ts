import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class PaymentService {
  private url = "http://localhost:8080/paypal";

  constructor(private http: HttpClient) {}

  makePayment(sum) {
    return this.http.post(this.url + "/make/payment?sum=" + sum, {});
  }

  completePayment(paymentId, payerId) {
    return this.http.post(
      this.url +
        "/complete/payment?paymentId=" +
        paymentId +
        "&payerId=" +
        payerId,
      {}
    );
  }
}
