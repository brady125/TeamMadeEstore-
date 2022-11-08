import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdminProductComponent } from './admin-product/admin-product.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserPageComponent } from './user-page/user-page.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component'
import { CheckoutPageComponent } from './checkout-page/checkout-page.component'

const routes: Routes = [
  { path: "", redirectTo: "/login", pathMatch: 'full'},
  { path: "sign-up", component: SignUpComponent },
  { path: "login", component: LoginComponent },
  { path: "admin-homepage", component: AdminPageComponent},
  { path: "user-homepage/:username", component: UserPageComponent },
  { path: "admin-product-page/:id", component: AdminProductComponent },
  { path: "shopping-cart/:username", component: ShoppingCartComponent },
  { path: "checkout-page/:username", component: CheckoutPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
