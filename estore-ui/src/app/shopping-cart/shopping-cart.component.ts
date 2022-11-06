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
  
  user: User | undefined; // honestly probably easier to just get and store the username
  // should have a products/cart field

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    // call get user and set the field
    this.getShoppingCart  // not currently calling the function (no parentheses)
  }

  getUser(){
      // I think its the username at the end of the url not the id
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10); 
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
  }

  getShoppingCart(user: User){  // shouldn't need paremeter if its a field
    this.userService.getCart(user.username).subscribe(/*products -> this.products = products*/)
      // do something like the comment in subscribe to set the products/cart field once you get it from the service
  }

  checkout(user: User): void{
      if (this.userService.getCart(user.username) != null){ //should be able to just check the products/cart field rather than calling the service again
        this.router.navigate(['checkout-page']);
      }
      // probably want some sort of message to appear if there are no products
      // or could have the checkout button be disabled by default and then enable it in ngOnInit 
        // if there are products in the shopping cart
  }

}
