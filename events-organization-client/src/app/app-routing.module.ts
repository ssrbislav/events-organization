import { MainPageComponent } from "./main-page/main-page.component";
import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";

const appRoutes: Routes = [
  { path: "", redirectTo: "/events", pathMatch: "full" },
  {
    path: "events",
    component: MainPageComponent
  },
  {
    path: "login",
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
