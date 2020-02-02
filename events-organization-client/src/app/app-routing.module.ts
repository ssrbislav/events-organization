import { MainPageComponent } from "./main-page/main-page.component";
import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AdminComponent } from "./admin/admin.component";

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
    component: AdminComponent
  },
  {
    path: "events",
    component: MainPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
