import { MainPageComponent } from "./main-page/main-page.component";
import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AdminComponent } from "./admin/admin.component";
import { RoleGuardService } from "./auth/role-guard.service";
import { EventInfoComponent } from "./main-page/event-info/event-info.component";
import { PaymentComponent } from "./payment/payment.component";
import { MakeReservationComponent } from "./main-page/event-info/make-reservation/make-reservation.component";
import { UserComponent } from "./user/user.component";

const appRoutes: Routes = [
  { path: "", redirectTo: "/main", pathMatch: "full" },
  {
    path: "main",
    component: MainPageComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "admin",
    component: AdminComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: "ROLE_ADMIN"
    }
  },
  {
    path: "events",
    component: MainPageComponent
  },
  {
    path: "event-info",
    component: EventInfoComponent
  },
  {
    path: "payment",
    component: PaymentComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: "ROLE_VISITOR"
    }
  },
  {
    path: "user",
    component: UserComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: "ROLE_VISITOR"
    }
  },
  {
    path: "make-reservation",
    component: MakeReservationComponent,
    canActivate: [RoleGuardService],
    data: {
      expectedRole: "ROLE_VISITOR"
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
