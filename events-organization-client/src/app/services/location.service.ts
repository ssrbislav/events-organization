import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Location, LocationDTO } from "../model/location.model";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class LocationService {
  private url = "http://localhost:8080/api/location";

  constructor(private http: HttpClient) {}

  getLocations() {
    return this.http.get<Location[]>(this.url);
  }

  getLocation(id: number) {
    return this.http.get<Location>(`${this.url}/${id}`);
  }

  createLocation(data: LocationDTO) {
    return this.http.post<Location>(`${this.url}/create`, data, httpOptions);
  }
}
