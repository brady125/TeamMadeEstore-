import { Component, OnInit } from '@angular/core';
import { Product } from "../product";
import { ProductService } from '../product.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  products: Product[] = []

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.search("")
  }

  search(searchTerm: string): void {
    this.productService.searchProducts(searchTerm).subscribe(p => this.products = p)
  }

}
