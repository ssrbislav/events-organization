import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { TicketDTO } from "../model/ticket.model";

@Injectable({
  providedIn: "root"
})
export class TicketService {
  private url = "http://localhost:8080/api";

  constructor(private http: HttpClient) {}

  makeReservation(tickets: TicketDTO[], price: number) {
    return this.http.post<any>(`${this.url}/reservation/${price}`, tickets);
  }

  cancelReservation(id: number) {
    return this.http.post<any>(`${this.url}/cancel/${id}`, {});
  }

  butTicket(id: number) {
    return this.http.post<any>(`${this.url}/buy/${id}`, {});
  }
}
