import { Component, OnInit } from '@angular/core';
import { Product } from "../product";
import { ProductService } from '../product.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    // initialize products with all products
    this.search("")
  }

  search(searchTerm: String): void {
    // modify products to only contain relevant products
    this.productService.searchProducts(searchTerm).subscribe(products => this.products = products)
  }


}
