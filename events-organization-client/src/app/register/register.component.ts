import { Component, OnInit } from "@angular/core";
import { SignupInfo } from "../auth/signup-info";
import { MAT_DATE_FORMATS, DateAdapter } from "@angular/material";
import {
  APP_DATE_FORMATS,
  AppDateAdapter
} from "../services/format-datepicker";

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
  minDate = new Date(1919, 6, 28);
  maxDate = new Date(2001, 6, 28);
  form: any = {};
  signupInfo: SignupInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = "";
  private roles: string[];

  constructor() {}

  ngOnInit() {}

  checkSame(pass: string) {
    this.form.passwordRepeat = pass;
    if (this.form.passwordRepeat !== this.form.password) {
      this.errorMessage = "Passwords do not match!";
    } else {
      this.errorMessage = "";
    }
  }
}
