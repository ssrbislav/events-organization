import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Sector } from "../model/sector.model";

@Injectable({
  providedIn: "root"
})
export class SectorService {
  private url = "http://localhost:8080/api/user/sector";

  constructor(private http: HttpClient) {}

  getSectors() {
    return this.http.get<Sector[]>(this.url);
  }
}
