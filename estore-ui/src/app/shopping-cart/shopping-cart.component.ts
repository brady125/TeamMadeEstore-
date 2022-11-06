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

  products: Product[] = []
  // probably want a username field

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    // get username from url (you can check the user homepage for reference)
    // use userService to get the user's shopping cart and assign to products field
      // will need to add this method to UserService
  }

  checkout(products: Product[], user: User): void{  // if products and username are both fields you don't need as parameters
      if (products.length > 0){
        this.router.navigate(['checkout-page/'+user.username]);
      }
      // probably want some sort of message to appear if there are no products
      // or could have the checkout button be disabled by default and then enable it in ngOnInit 
        // if there are products in the shopping cart
  }

}
