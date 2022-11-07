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

  username: string = ""
  products: Product[] = []

  constructor(private route: ActivatedRoute, private userService: UserService, private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    // call get user and set the field
    this.getUser(this.username)
    this.getShoppingCart()  // not currently calling the function (no parentheses)
  }

  getUser(username: string){
    const id = parseInt(this.route.snapshot.paramMap.get('username')!, 10); 
    this.userService.getUser(id)
      .subscribe(user => this.username = user);
  }

  getShoppingCart(){
    this.userService.getCart(this.username).subscribe(p => this.products = p)
      // do something like the comment in subscribe to set the products/cart field once you get it from the service
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
