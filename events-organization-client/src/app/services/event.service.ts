import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Event, EventDTO } from "../model/event.model";
import { EventSector, EventSectorDTO } from "../model/eventSector.model";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class EventService {
  private url = "http://localhost:8080/api/event";
  private url2 = "http://localhost:8080/api/event_sector";

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

  createEventSector(data: EventSectorDTO) {
    return this.http.post<EventSector>(
      `${this.url2}/create`,
      data,
      httpOptions
    );
  }

  getEventSectorByEvent(id: number) {
    return this.http.get<EventSector[]>(`${this.url2}/all/${id}`);
  }
}
