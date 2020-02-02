import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Event, EventDTO } from "../model/event.model";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class EventService {
  private url = "http://localhost:8080/api/event";

  constructor(private http: HttpClient) {}

  getEvents() {
    return this.http.get<Event>(this.url);
  }

  getEvent(id: number) {
    return this.http.get<Event>(`${this.url}/${id}`);
  }

  createEvent(data: EventDTO) {
    return this.http.post<Event>(`${this.url}/create`, data, httpOptions);
  }
}
