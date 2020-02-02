import { Component, OnInit } from "@angular/core";
import { TokenStorageService } from "../auth/ttoken-storage.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-main-page",
  templateUrl: "./main-page.component.html",
  styleUrls: ["./main-page.component.css"]
})
export class MainPageComponent implements OnInit {
  roles: string[];

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.navigate();
  }

  navigate() {
    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === "ROLE_ADMIN") {
          this.router.navigate(["admin"]);
          return true;
        } else if (role === "ROLE_VISITOR") {
          this.router.navigate(["events"]);
          return true;
        }
      });
    }
  }
}
