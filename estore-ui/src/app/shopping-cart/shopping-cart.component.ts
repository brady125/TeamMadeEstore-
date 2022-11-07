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

  // username: string = ""-- since the shopping cart is stored in the user it's probably better to go back to storing the whole User my bad
  user!: User
  products: Product[] = []

  constructor(private route: ActivatedRoute, private userService: UserService, private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    // call get user and set the field
    this.getUser()
    this.getShoppingCart()
  }

  getUser(){
    // const id = parseInt(this.route.snapshot.paramMap.get('username')!, 10);-- changed to get username since the username is part of the path not the id
    const username = this.router.url.split("/").pop()!
    this.userService.getUser(username)
      .subscribe(user => this.user = user);
  }

  getShoppingCart(){
    // this.userService.getCart(this.user.username).subscribe(p => this.products = p)
      // do something like the comment in subscribe to set the products/cart field once you get it from the service
    // scrach all that it's already in the user
    this.products = this.user.shoppingCart
  }

  checkout(): void{
      if (this.products.length > 0){
        this.router.navigate(['checkout-page']);
      }
      // probably want some sort of message to appear if there are no products
      // or could have the checkout button be disabled by default and then enable it in ngOnInit 
        // if there are products in the shopping cart
  }

}
