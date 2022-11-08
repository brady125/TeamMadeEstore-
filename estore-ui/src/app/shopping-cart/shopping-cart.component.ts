import { Component, OnInit } from '@angular/core';
import { Product } from "../product";
import {ProductService } from '../product.service'
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user'

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  user!: User
  products: Product[] = []
  errorMessage = "";
  display = "none";

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getUser();
    this.getShoppingCart();
  }

  getUser(){
    const username = this.router.url.split("/").pop()!
    this.userService.getUser(username)
      .subscribe(user => this.user = user);
  }

  getShoppingCart(){
    this.products = this.user.shoppingCart;
  }

  checkout(): void{
      if (this.products.length > 0){
        this.router.navigate(['checkout-page']);
      }
      else{
        this.errorMessage = "no items";
        this.display = "initial";
      }
  }

}
