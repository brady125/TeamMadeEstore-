import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent implements OnInit {

  product: Product = {
    id: 0,
    name: "",
    species: "",
    age: 0,
    color: "",
    description: "",
    price: 0
  }
  message = ""

  constructor(private router: Router, private productService: ProductService) { }

  ngOnInit(): void {
    var url = this.router.url
    this.product.id = parseInt(url[url.length-1])
    if (this.product.id != 0) {
      // load existing info on product
      this.productService.getProduct(this.product.id).subscribe(p => this.product = p)
    }
  }

  save() {
    // commits product to inventory, either updating existing or creating new
    if (this.product.id == 0) {
      this.productService.createProduct(this.product).subscribe(_ => this.message = "Saved!", err => this.message = "Something went wrong")
    } else {
      this.productService.updateProduct(this.product).subscribe(_ => this.message = "Saved!", err => this.message = "Something went wrong")
    }
  }


}
