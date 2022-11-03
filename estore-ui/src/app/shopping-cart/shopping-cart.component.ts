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

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  checkout(products: Product[], user: User): void{
      if (products.length > 0){
        this.router.navigate(['checkout-page/'+user.username]);
      }
  }

}
