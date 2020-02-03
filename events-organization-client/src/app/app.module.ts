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
  MatInputModule,
  MatSortModule,
  MatTableModule,
  MatIconModule,
  MatListModule,
  MatGridListModule,
  MatOptionModule,
  MatSelectModule,
  MatButtonModule,
  MatDialogModule,
  MAT_DIALOG_DATA,
  MatDialogRef
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
import { SectorListComponent } from "./admin/location-list/hall-list/sector-list/sector-list.component";
import { HallListComponent } from "./admin/location-list/hall-list/hall-list.component";
import { LocationListComponent } from "./admin/location-list/location-list.component";
import { AddSectorComponent } from "./admin/location-list/hall-list/sector-list/add-sector/add-sector.component";
import { EditSectorComponent } from "./admin/location-list/hall-list/sector-list/edit-sector/edit-sector.component";
import { AddLocationComponent } from "./admin/location-list/add-location/add-location.component";
import { AddHallComponent } from "./admin/location-list/hall-list/add-hall/add-hall.component";
import { AddEventComponent } from "./admin/event-list/add-event/add-event.component";
import { EventsComponent } from './main-page/events/events.component';

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
    LocationListComponent,
    AddSectorComponent,
    EditSectorComponent,
    AddLocationComponent,
    AddHallComponent,
    AddEventComponent,
    EventsComponent
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
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatGridListModule,
    MatListModule,
    MatIconModule,
    MatTableModule,
    MatSortModule,
    MatDialogModule,
    MatButtonModule
  ],
  entryComponents: [
    HallListComponent,
    SectorListComponent,
    AddSectorComponent,
    EditSectorComponent,
    AddLocationComponent,
    AddHallComponent,
    AddEventComponent
  ],
  providers: [
    RoleGuardService,
    httpInterceptorProviders,
    { provide: MatDialogRef, useValue: {} },
    { provide: MAT_DIALOG_DATA, useValue: [] },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
