import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../product';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  username: string = ""
  products: Product[] = []

  constructor(private router: Router, private productService: ProductService) { }

  ngOnInit(): void {
    var url = this.router.url.split("/")
    this.username = url.pop()!
    this.search("")
  }

  search(searchTerm: string): void {
    this.productService.searchProducts(searchTerm).subscribe(p => this.products = p)
  }

}
