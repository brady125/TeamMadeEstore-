import { Component, Input, OnInit } from '@angular/core';
import { Product } from "../product";
import { ProductService } from '../product.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  @Input() product!: Product;
  @Input() username!: string;
  @Input() admin!: Boolean;
  @Input() cartPage!: Boolean;
  deleted = false
  user!: User
  // whether the product is in the shopping cart
  inShoppingCart = false

  constructor(private productService: ProductService, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUser(this.username).subscribe(user => 
      {this.user = user;
      this.inShoppingCart = this.user.shoppingcart.includes(this.product.id);})
  }

  delete() {
    this.productService.deleteProduct(this.product.id).subscribe(_ => this.deleted = true)
  }

  addToShoppingCart() {
    this.userService.getUser(this.username).subscribe(user => {this.user = user;
      this.user.shoppingcart.push(this.product.id);
      this.userService.updateUser(this.user).subscribe(user => console.log(user.shoppingcart));})
    this.inShoppingCart = true;
  }

  removeFromShoppingCart() {
    this.userService.getUser(this.username).subscribe(user => {this.user = user;
      const index = this.user.shoppingcart.indexOf(this.product.id);
      this.user.shoppingcart.splice(index, 1);
      this.userService.updateUser(this.user).subscribe(user => console.log(user.shoppingcart));})
    this.inShoppingCart = false;
  }

}
