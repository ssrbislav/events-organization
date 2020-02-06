import { HttpClient, HttpHeaders } from "@angular/common/http";
import { LoginInfo } from "../auth/login-info";
import { JwtResponse } from "../auth/jwt-response";
import { Observable } from "rxjs";
import { SignupInfo } from "../auth/signup-info";
import { Injectable } from "@angular/core";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable({
  providedIn: "root"
})
export class AuthService {
  private loginUrl = "http://localhost:8080/api/user/login";
  private registrationUrl = "http://localhost:8080/api/user/register";

  constructor(private http: HttpClient) {}

  attemptAuth(credentials: LoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignupInfo) {
    return this.http.post<any>(this.registrationUrl, info, httpOptions);
  }
}
