import { MainPageComponent } from "./main-page/main-page.component";
import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AdminComponent } from "./admin/admin.component";
import { RoleGuardService } from "./auth/role-guard.service";

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
    component: MainPageComponent,
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
