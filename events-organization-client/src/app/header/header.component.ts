import { Component, OnInit } from "@angular/core";
import { TokenStorageService } from "../auth/ttoken-storage.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.css"]
})
export class HeaderComponent implements OnInit {
  showView = "";
  private roles: string[];
  username: string;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAuthFromStorage();
  }

  getAuthFromStorage() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.username = this.tokenStorage.getUsername();
      this.roles.every(role => {
        this.showView = "visitor";
        if (role === "ROLE_ADMIN") {
          this.showView = "admin";
          return true;
        } else if (role === "ROLE_VISITOR") {
          this.showView = "visitor";
          return true;
        }
      });
    }
  }

  logout() {
    window.sessionStorage.clear();
    location.reload();
  }
}
