import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AdminProductComponent } from './admin-product/admin-product.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserPageComponent } from './user-page/user-page.component';
import { WelcomePageComponent } from './welcome-page/welcome-page.component';
import { CanDeactivateGuard } from './routing-guard';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';

const routes: Routes = [
  { path: "", redirectTo: "/welcome-page", pathMatch: 'full'},
  { path: "welcome-page", component: WelcomePageComponent },
  { path: "sign-up", component: SignUpComponent, canDeactivate: [CanDeactivateGuard]},
  { path: "login", component: LoginComponent, canDeactivate: [CanDeactivateGuard]},
  { path: "admin-homepage", component: AdminPageComponent},
  { path: "user-homepage/:username", component: UserPageComponent },
  { path: "admin-product/:id", component: AdminProductComponent },
  { path: "shopping-cart/:username", component: ShoppingCartComponent },
  { path: "checkout-page/:username", component: CheckoutPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [CanDeactivateGuard]
})
export class AppRoutingModule { }
