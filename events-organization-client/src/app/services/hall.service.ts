import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Hall } from "../model/hall.model";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class HallService {
  private url = "http://localhost:8080/api/hall";

  constructor(private http: HttpClient) {}

  getHalls() {
    return this.http.get<Hall[]>(this.url);
  }
}
