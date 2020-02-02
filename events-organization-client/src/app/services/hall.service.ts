import { Injectable } from "@angular/core";
import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Hall, HallDTO } from "../model/hall.model";

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

  getHall(id: number) {
    return this.http.get<Hall>(`${this.url}/${id}`);
  }

  createHall(data: HallDTO) {
    return this.http.post<Hall>(`${this.url}/create`, data, httpOptions);
  }
}
