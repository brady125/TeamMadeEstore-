import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdminProductComponent } from './admin-product/admin-product.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserPageComponent } from './user-page/user-page.component';

const routes: Routes = [
  { path: "", redirectTo: "/sign-up", pathMatch: 'full'},
  { path: "sign-up", component: SignUpComponent },
  { path: "login", component: LoginComponent },
  { path: "admin-homepage", component: AdminPageComponent},
  { path: "user-homepage/:username", component: UserPageComponent },
  { path: "admin-product-page/:id", component: AdminProductComponent },
  // { path: "user-product-page/:userid/:id", component: UserProductComponent },
  // { path: "shopping-cart/:userid", component: ShoppingCartComponent }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
