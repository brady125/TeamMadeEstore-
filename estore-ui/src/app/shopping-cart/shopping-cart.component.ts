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
  
  user: User | undefined;

  constructor(private route: ActivatedRoute, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getShoppingCart
  }

  getUser(){
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
  }

  getShoppingCart(user: User){
    this.userService.getCart(user.username).subscribe()
  }

  checkout(user: User): void{
      if (this.userService.getCart(user.username) != null){
        this.router.navigate(['checkout-page']);
      }
      // probably want some sort of message to appear if there are no products
      // or could have the checkout button be disabled by default and then enable it in ngOnInit 
        // if there are products in the shopping cart
  }

}
