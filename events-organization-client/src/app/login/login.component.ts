import { Component, OnInit } from "@angular/core";
import { LoginInfo } from "../auth/login-info";
import { TokenStorageService } from "../auth/ttoken-storage.service";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = "";
  roles: string[] = [];
  private loginInfo: LoginInfo;
  private active = false;
  private username: string;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (this.tokenStorage.getToken() !== null) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.navigate();
    }
  }

  onSubmit() {
    this.loginInfo = new LoginInfo(this.form.username, this.form.password);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.roles = this.tokenStorage.getAuthorities();
        this.roles.every(role => {
          if (role === "ROLE_ADMIN") {
            this.router.navigate([""]);
            return true;
          } else if (role === "ROLE_VISITOR") {
            this.router.navigate([""]);
            return true;
          }
        });
        location.reload();
      },
      error => {
        console.log(error);
        window.alert("Wrong credentials!");
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  navigate() {
    this.roles.every(role => {
      if (role === "ROLE_ADMIN") {
        this.router.navigate([""]);
        return true;
      } else if (role === "ROLE_VISITOR") {
        this.router.navigate([""]);
        return true;
      }
    });
  }
}
