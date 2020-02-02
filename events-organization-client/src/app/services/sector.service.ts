import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Sector, SectorDTO } from "../model/sector.model";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class SectorService {
  private url = "http://localhost:8080/api/sector";

  constructor(private http: HttpClient) {}

  getSectors() {
    return this.http.get<Sector[]>(this.url);
  }

  getSector(id: number) {
    return this.http.get<Sector>(`${this.url}/${id}`);
  }

  createSector(data: SectorDTO) {
    return this.http.post<Sector>(`${this.url}/create`, data, httpOptions);
  }

  updateSector(data: Sector) {
    return this.http.put<Sector>(`${this.url}/update`, data, httpOptions);
  }
}
