import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Location } from "../model/location.model";

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
}
