import { Component, OnInit } from '@angular/core';
import { Product } from "../product";
import { ProductService } from '../product.service'
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
  deleted = false;

  constructor(private userService: UserService, private router: Router, private productService: ProductService) { }

  ngOnInit(): void {
    this.getUser();
  }
  
  search(searchTerm: string): void {
    this.productService.searchProducts(searchTerm).subscribe(p => this.products = p)
  }

  getUser(){
    const username = this.router.url.split("/").pop()!
    this.userService.getUser(username)
      .subscribe(user => {this.user = user; this.getShoppingCart();});
  }

  getShoppingCart(){
    this.products = [];
    for (var productID of this.user.shoppingcart) {
      this.productService.getProduct(productID).subscribe(product => this.products.push(product))
    }
  }

  checkout(): void{
      if (this.products.length > 0){
        // should also remove items from inventory and shopping cart
        for (var product of this.products) {
          this.productService.deleteProduct(product.id).subscribe(_ => this.deleted = true)
        }
        this.router.navigate(['checkout-page', this.user.username]);
      }
      else{
        this.errorMessage = "no items";
        this.display = "initial";
      }
  }

}
