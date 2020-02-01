import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MainPageComponent } from "./main-page/main-page.component";
import { HeaderComponent } from "./header/header.component";
import { AppRoutingModule } from "./app-routing.module";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import {
  MatDatepickerModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatInputModule
} from "@angular/material";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import {
  AuthInterceptor,
  httpInterceptorProviders
} from "./auth/auth-interceptor";
import { RoleGuardService } from "./auth/role-guard.service";
import { AdminComponent } from "./admin/admin.component";
import { EventListComponent } from "./admin/event-list/event-list.component";
import { SectorListComponent } from "./admin/sector-list/sector-list.component";
import { HallListComponent } from "./admin/hall-list/hall-list.component";
import { LocationListComponent } from "./admin/location-list/location-list.component";

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    AdminComponent,
    EventListComponent,
    SectorListComponent,
    HallListComponent,
    LocationListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    RoleGuardService,
    httpInterceptorProviders,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
