import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, Observable, Subject, switchMap , of, map} from 'rxjs';
import { Product } from "../product";
import { ProductService } from '../product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  products: Product[] = []

  constructor(private productService: ProductService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.search("")
  }

  search(searchTerm: string): void {
    this.productService.searchProducts(searchTerm).subscribe(p => this.products = p)
  }


}
