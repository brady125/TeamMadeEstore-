import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, Observable, Subject, switchMap } from 'rxjs';
import { Product } from "../product";
import { ProductService } from '../product.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  products$!: Observable<Product[]>;
  private searchTerms = new Subject<string>();

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.products$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.productService.searchProducts(term)),
    );
    // initialize products with all products
    this.search("")
  }

  search(searchTerm: string): void {
    // modify products to only contain relevant products
    // this.productService.searchProducts(searchTerm).subscribe(products => this.products = products)
    this.searchTerms.next(searchTerm)
  }


}
