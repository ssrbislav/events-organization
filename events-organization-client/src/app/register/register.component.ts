import { Component, OnInit } from "@angular/core";
import { SignupInfo } from "../auth/signup-info";
import { MAT_DATE_FORMATS, DateAdapter } from "@angular/material";
import {
  APP_DATE_FORMATS,
  AppDateAdapter
} from "../services/format-datepicker";
import { TokenStorageService } from "../auth/ttoken-storage.service";
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class RegisterComponent implements OnInit {
  minDate = new Date(1919, 1, 28);
  maxDate = new Date(2002, 1, 28);
  form: any = {};
  signupInfo: SignupInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = "";
  private roles: string[];

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    if (this.tokenStorage.getToken() !== null) {
      this.navigate();
    }
  }

  onSubmit() {
    this.signupInfo = new SignupInfo(
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.firstName,
      this.form.lastName,
      this.form.dateOfBirth,
      this.form.address,
      this.form.phoneNumber
    );

    console.log(this.signupInfo);
    this.authService.signUp(this.signupInfo).subscribe(
      () => {
        window.alert(
          "Registration successfull! \nPlease activate your account!"
        );
        this.isSignUpFailed = false;
        this.isSignedUp = true;
        this.router.navigate(["login"]);
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  checkSame(pass: string) {
    this.form.passwordRepeat = pass;
    if (this.form.passwordRepeat !== this.form.password) {
      this.errorMessage = "Passwords do not match!";
    } else {
      this.errorMessage = "";
    }
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
