import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { TicketDTO } from "../model/ticket.model";

@Injectable({
  providedIn: "root"
})
export class TicketService {
  private url = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  makeReservation(tickets: TicketDTO[]) {
    this.http.post(`${this.url}/reservation`, tickets);
  }

  cancelReservation(id: number) {
    this.http.post(`${this.url}/cancel/${id}`, {});
  }

  butTicket(id: number) {
    this.http.post(`${this.url}/buy/${id}`, {});
  }
}
